package com.ruoyi.common.utils;

import io.swagger.models.auth.In;

import java.util.Random;

/**
 * @author Sunss
 * @since 2020/5/22
 */
public final class CommonUtil {
    public static Integer getRandom(Integer x, Integer y) {
        return new Random().nextInt(y + 1 - x) + x;
    }
}
