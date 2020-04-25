package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 关注用户对象 ci_user_concern_user
 *
 * @author sun
 * @date 2020-04-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserConcernUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    private SysUser assUser;

}
