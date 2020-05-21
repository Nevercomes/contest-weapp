package com.ruoyi.project.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户扩展信息对象 sys_user_ext
 *
 * @author sun
 * @date 2020-05-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserExt extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户 */
    private Long userId;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String intro;


}
