package com.ruoyi.framework.security.service;

import javax.annotation.Resource;

import com.ruoyi.common.exception.user.OpenIdNotFoundException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.common.WeAppUtil;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 使用code进行微信登录
     *
     * @param code
     * @return
     */
    public Map<String, Object> wxLogin(String code) {
        String openId = WeAppUtil.getOpenId(code);
        Map<String, Object> res = new HashMap<>();
        res.put("register", false);
        if (StringUtils.isNotEmpty(openId)) {
            // 通过openId查找用户
            SysUser user = userService.selectUserByUserName(openId);
            String pwd = "";
            if (user == null) {
                // 根据openId创建用户
                user = userService.createWeappUser(openId, pwd);
                res.put("register", true);
            }
            Authentication authentication = null;
            try {
                // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(openId, pwd));
            } catch (Exception e) {
                if (e instanceof BadCredentialsException) {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(openId, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                    throw new UserPasswordNotMatchException();
                } else {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(openId, Constants.LOGIN_FAIL, e.getMessage()));
                    throw new CustomException(e.getMessage());
                }
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(openId, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            // 生成token
            res.put("token", tokenService.createToken(loginUser));
            return res;
        } else {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor("weappUser", Constants.LOGIN_FAIL, MessageUtils.message("user.openid.notfound")));
            throw new OpenIdNotFoundException();
        }
    }
}
