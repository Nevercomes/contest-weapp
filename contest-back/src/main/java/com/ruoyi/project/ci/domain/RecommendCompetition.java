package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 竞赛推荐对象 ci_recommend_competition
 *
 * @author sun
 * @date 2020-05-31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendCompetition extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Long id;

    /**
     * 图片Url
     */
    @Excel(name = "图片Url")
    private String picUrl;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer orderNum;

    /**
     * 竞赛
     */
    private Long cpId;
    @Excel(name = "竞赛")
    private String cpName;

    /**
     * 分类标签
     */
    private String classifyLabels;


}
