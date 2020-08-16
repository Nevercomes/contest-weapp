package com.ruoyi.project.system.service.impl;

import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.service.ISysUserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sunss
 * @since 2020/8/14
 */
@Service
public class SysUserSettingServiceImpl implements ISysUserSettingService {

    @Autowired
    private RedisCache redisCache;


    @Override
    public int updateOnlySchool(boolean onlySchool) {
        return 1;
    }

    @Override
    public int updateMsgNotice(boolean onlySchool) {
        return 1;
    }
}
