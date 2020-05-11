package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * popular对象 ci_search_popular
 *
 * @author sun
 * @date 2020-05-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchPopular extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 关键词 */
    @Excel(name = "关键词")
    private String keyword;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 类别 */
    @Excel(name = "类别")
    private String type;


}
