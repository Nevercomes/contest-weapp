package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 队伍信息对象 ci_team_info
 *
 * @author sun
 * @date 2020-04-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamInfo extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 比赛 */
    private Long cpId;
    @Excel(name = "比赛")
    private String cpName;

    /** 队伍名 */
    @Excel(name = "队伍名")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /**
     * 添加队伍头像
     */
    private String picUrl;

}
