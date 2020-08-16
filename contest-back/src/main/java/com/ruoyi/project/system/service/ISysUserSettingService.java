package com.ruoyi.project.system.service;

/**
 * 用户设置的service 包括校内浏览等
 * @author Sunss
 * @since 2020/8/14
 */
public interface ISysUserSettingService {
    int updateOnlySchool(boolean onlySchool);

    int updateMsgNotice(boolean onlySchool);
}
