package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户显示设置对象 ci_user_show_setting
 *
 * @author sun
 * @date 2020-05-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserShowSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long ownUserId;

    /** 显示名字 */
    @Excel(name = "显示名字")
    private String showName;

    /** 显示简介 */
    @Excel(name = "显示简介")
    private String showIntro;

    /** 显示经历 */
    @Excel(name = "显示经历")
    private String showExperience;

    /** 显示积分 */
    @Excel(name = "显示积分")
    private String showPoints;

    /** 显示动态 */
    @Excel(name = "显示动态")
    private String showNews;


}
