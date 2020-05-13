package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.UserShowSetting;

/**
 * 用户显示设置Service接口
 *
 * @author sun
 * @date 2020-05-13
 */
public interface IUserShowSettingService {
    /**
     * 查询用户显示设置
     *
     * @param ownUserId 用户显示设置ID
     * @return 用户显示设置
     */
    UserShowSetting selectUserShowSettingById(Long ownUserId);

    /**
     * 查询用户显示设置列表
     *
     * @param userShowSetting 用户显示设置
     * @return 用户显示设置集合
     */
    List<UserShowSetting> selectUserShowSettingList(UserShowSetting userShowSetting);

    /**
     * 查询用户显示设置选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectUserShowSettingOptions(UserShowSetting userShowSetting);

    /**
     * 新增用户显示设置
     *
     * @param userShowSetting 用户显示设置
     * @return 结果
     */
    int insertUserShowSetting(UserShowSetting userShowSetting);

    /**
     * 修改用户显示设置
     *
     * @param userShowSetting 用户显示设置
     * @return 结果
     */
    int updateUserShowSetting(UserShowSetting userShowSetting);

    /**
     * 批量删除用户显示设置
     *
     * @param ownUserIds 需要删除的用户显示设置ID
     * @return 结果
     */
    int deleteUserShowSettingByIds(Long[] ownUserIds);

    /**
     * 删除用户显示设置信息
     *
     * @param ownUserId 用户显示设置ID
     * @return 结果
     */
    int deleteUserShowSettingById(Long ownUserId);
}
