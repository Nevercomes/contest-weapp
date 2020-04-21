package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 队伍成员对象 ci_team_member
 *
 * @author sun
 * @date 2020-04-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamMember extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 队员 */
    @Excel(name = "队员")
    private Long userId;
    /**
     * 相关用户
     */
    private SysUser assUser;

    /** 队伍 */
    @Excel(name = "队伍")
    private Long teamId;
    private String teamName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 身份 */
    @Excel(name = "身份")
    private String identity;

    /** 分工 */
    @Excel(name = "分工")
    private String work;

    /** 能力 */
    @Excel(name = "能力")
    private String capacity;

}
