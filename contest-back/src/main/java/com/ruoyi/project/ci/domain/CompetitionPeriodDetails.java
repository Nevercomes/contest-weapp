package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 竞赛详情对象 ci_competition_period_details
 *
 * @author sun
 * @date 2020-04-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionPeriodDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 竞赛 */
    @Excel(name = "竞赛")
    private Long cpId;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 官网地址 */
    @Excel(name = "官网地址")
    private String officialLink;


}
