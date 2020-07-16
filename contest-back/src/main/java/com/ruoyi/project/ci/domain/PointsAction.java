package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 积分记录对象 ci_points_action
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PointsAction extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 变化原因 */
    @Excel(name = "变化原因")
    private String changeCause;

    /** 值 */
    @Excel(name = "值")
    private Integer value;

    /** 归属用户 */
    @Excel(name = "归属用户")
    private Long userId;

    private SysUser assUser;

}
