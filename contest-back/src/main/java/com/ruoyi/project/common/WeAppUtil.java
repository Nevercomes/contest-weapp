package com.ruoyi.project.common;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 小程序通用工具类
 *
 * @author Sunss
 * @since 2020/4/28
 */
public final class WeAppUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    //    个人主体体验版
//    private final static String APP_ID = "wxf4a68b53013e8def";
//    private final static String APP_SECRET = "f3546751d8cb7e9ca6be6f3a14d6c536";
    //    企业主体发布版
    private final static String APP_ID = "wx54f5d0b96853b41b";
    private final static String APP_SECRET = "a46c3b577431a99dcee6f97cf2f55dd6";
    private final static String JSCODE_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static String getOpenId(String code) {
        try {
            String param = "appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code
                    + "&grant_type=authorization_code";
            String accessJson = HttpUtils.sendGet(JSCODE_SESSION_URL, param);
            JSONObject jsonObject = JSONObject.parseObject(accessJson);
            String openId = jsonObject.getString("openid");
            if (StringUtils.isEmpty(openId)) {
                String errCode = jsonObject.getString("errcode");
                String errMsg = jsonObject.getString("errmsg");
                log.warn("获取openId失败, code:{}, msg:{}", errCode, errMsg);
            }
            return openId;

        } catch (Exception e) {
            log.error("获取openId发生错误:{}", e.getMessage());
            return null;
        }
    }
}
