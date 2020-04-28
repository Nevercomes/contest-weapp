package com.ruoyi.common.exception.user;

/**
 * @author Sunss
 * @since 2020/4/28
 */
public class OpenIdNotFoundException extends UserException {
    public OpenIdNotFoundException() {
        super("user.openid.notfound", null);
    }
}
