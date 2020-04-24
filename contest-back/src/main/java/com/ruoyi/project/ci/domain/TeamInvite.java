package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 入队邀请对象 ci_team_invite
 *
 * @author sun
 * @date 2020-04-24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamInvite extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 队伍 */
    private Long teamId;
    @Excel(name = "队伍")
    private String teamName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 邀请对象 */
    @Excel(name = "邀请对象")
    private Long invitedUserId;

    private SysUser assUser;

}
