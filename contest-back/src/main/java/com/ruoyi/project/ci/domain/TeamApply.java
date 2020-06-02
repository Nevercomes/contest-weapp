package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 入队申请对象 ci_team_apply
 *
 * @author sun
 * @date 2020-04-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Long id;

    /**
     * 队伍
     */
    private Long teamId;
    @Excel(name = "队伍")
    private String teamName;

    private String work;

    private String capability;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    private SysUser createUser;

}
