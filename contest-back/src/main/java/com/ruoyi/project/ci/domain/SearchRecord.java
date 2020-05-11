package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * searchRecord对象 ci_search_record
 *
 * @author sun
 * @date 2020-05-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 关键词 */
    @Excel(name = "关键词")
    private String keyword;

    /** 类别 */
    @Excel(name = "类别")
    private String type;


}
