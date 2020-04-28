package com.ruoyi.project.common;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 小程序通用工具类
 *
 * @author Sunss
 * @since 2020/4/28
 */
public final class WeAppUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    @Value("${weapp.appId}")
    private static String appId;
    @Value("${weapp.appSecret}")
    private static String appSecret;
    @Value("${weapp.jscode2session}")
    private static String jscode2sessionUrl;

    public static String getOpenId(String code) {
        try {
            String param = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code
                    + "&grant_type=authorization_code";
            String accessJson = HttpUtils.sendGet(jscode2sessionUrl, param);
            JSONObject jsonObject = JSONObject.parseObject(accessJson);
            String errCode = jsonObject.getString("errcode");
            String errMsg = jsonObject.getString("errmsg");
            if (!"0".equals(errCode)) {
                log.error("获取openId失败, code:{}, msg:{}", errCode, errMsg);
            }
            return jsonObject.getString("openid");

        } catch (Exception e) {
            log.error("获取openId发生错误:{}", e.getMessage());
            return null;
        }
    }
}
