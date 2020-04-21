package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 队员招募对象 ci_team_recruit
 *
 * @author sun
 * @date 2020-04-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamRecruit extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 队伍 */
    private Long teamId;
    @Excel(name = "队伍")
    private String teamName;

    /** 分工 */
    @Excel(name = "分工")
    private String work;

    /** 能力 */
    @Excel(name = "能力")
    private String capacity;


}
