package com.ruoyi.project.ci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 竞赛列表信息对象 ci_competition_period
 *
 * @author sun
 * @date 2020-04-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionPeriod extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Long id;

    /**
     * 竞模板
     */
    @Excel(name = "竞模板")
    private Long cpInfoId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 图片链接
     */
    private String picUrl;

    /**
     * 报名时间
     */
    @Excel(name = "报名开始", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signBeginTime;

    /**
     * 报名结束
     */
    @Excel(name = "报名结束", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signEndTime;

    /**
     * 面向学校
     */
    private Long schoolId;
    @Excel(name = "面向学校")
    private String schoolName;

    /**
     * 主办方
     */
    @Excel(name = "主办方")
    private String holder;

    /**
     * 分类信息
     */
    @Excel(name = "分类信息")
    private String classifyLabels;

    /**
     * 基本信息
     */
    private CompetitionInfo basic;

    /**
     * 用于搜索的字段
     */
    private String level;
    private Long classify;
    private String sortWay;
    private boolean onlySchool;
    private Date signTime;

    /**
     * 详情信息
     */
    private CompetitionPeriodDetails details;


}
