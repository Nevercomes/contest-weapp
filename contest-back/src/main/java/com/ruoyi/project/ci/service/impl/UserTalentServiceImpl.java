package com.ruoyi.project.ci.service.impl;

import com.ruoyi.project.ci.service.IUserExperienceService;
import com.ruoyi.project.ci.service.IUserTalentService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竞赛达人service impl
 * @author Sunss
 * @since 2020/8/9
 */
@Service
public class UserTalentServiceImpl implements IUserTalentService {

    @Autowired
    private IUserExperienceService userExperienceService;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserTalentList() {
        List<String> usernameList = userExperienceService.selectUsernameTalentList();
        return userMapper.selectUserListByUsername(usernameList);
    }
}
