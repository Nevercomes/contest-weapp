package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 竞赛信息模板对象 ci_competition_info
 *
 * @author sun
 * @date 2020-04-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionInfo extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 竞赛名称 */
    @Excel(name = "竞赛名称")
    private String name;

    /** 竞赛级别 */
    @Excel(name = "竞赛级别")
    private String level;

    /** 浏览数 */
    @Excel(name = "浏览数")
    private Long viewNumber;

    /** 关注数 */
    @Excel(name = "关注数")
    private Long concernNumber;

    /**
     * 竞赛分类Id列表
     */
    private Long[] classifyIds;

}
