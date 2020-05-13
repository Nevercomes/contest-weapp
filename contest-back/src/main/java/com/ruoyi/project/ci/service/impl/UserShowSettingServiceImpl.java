package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.UserShowSettingMapper;
import com.ruoyi.project.ci.domain.UserShowSetting;
import com.ruoyi.project.ci.service.IUserShowSettingService;

/**
 * 用户显示设置Service业务层处理
 *
 * @author sun
 * @date 2020-05-13
 */
@Service
public class UserShowSettingServiceImpl implements IUserShowSettingService {
    @Autowired
    private UserShowSettingMapper userShowSettingMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询用户显示设置
     *
     * @param ownUserId 用户显示设置ID
     * @return 用户显示设置
     */
    @Override
    public UserShowSetting selectUserShowSettingById(Long ownUserId) {
        return userShowSettingMapper.selectUserShowSettingById(ownUserId);
    }

    /**
     * 查询用户显示设置列表
     *
     * @param userShowSetting 用户显示设置
     * @return 用户显示设置
     */
    @Override
    public List<UserShowSetting> selectUserShowSettingList(UserShowSetting userShowSetting) {
        return userShowSettingMapper.selectUserShowSettingList(userShowSetting);
    }

    /**
     * 查询用户显示设置选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectUserShowSettingOptions(UserShowSetting userShowSetting) {
        return userShowSettingMapper.selectUserShowSettingOptions(userShowSetting);
    }

    /**
     * 新增用户显示设置
     *
     * @param userShowSetting 用户显示设置
     * @return 结果
     */
    @Override
    public int insertUserShowSetting(UserShowSetting userShowSetting) {
        userShowSetting.preInsert();
        return userShowSettingMapper.insertUserShowSetting(userShowSetting);
    }

    /**
     * 修改用户显示设置
     *
     * @param userShowSetting 用户显示设置
     * @return 结果
     */
    @Override
    public int updateUserShowSetting(UserShowSetting userShowSetting) {
        userShowSetting.preUpdate();
        return userShowSettingMapper.updateUserShowSetting(userShowSetting);
    }

    /**
     * 批量删除用户显示设置
     *
     * @param ownUserIds 需要删除的用户显示设置ID
     * @return 结果
     */
    @Override
    public int deleteUserShowSettingByIds(Long[] ownUserIds) {
        return userShowSettingMapper.deleteUserShowSettingByIds(ownUserIds);
    }

    /**
     * 删除用户显示设置信息
     *
     * @param ownUserId 用户显示设置ID
     * @return 结果
     */
    @Override
    public int deleteUserShowSettingById(Long ownUserId) {
        return userShowSettingMapper.deleteUserShowSettingById(ownUserId);
    }

    /**
     * 注入创建者
     * @param userShowSetting 注入对象
     */
    private void setCreated(UserShowSetting userShowSetting) {
        if(userShowSetting == null) return;
        if (StringUtils.isNotEmpty(userShowSetting.getCreateBy())) {
           // userShowSetting.setCreateUser(userMapper.selectCreateUser(userShowSetting.getCreateBy()));
        }
    }
}
