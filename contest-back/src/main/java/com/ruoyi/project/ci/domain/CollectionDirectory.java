package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 收藏夹对象 ci_collection_directory
 *
 * @author sun
 * @date 2020-06-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionDirectory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 目录名称 */
    @Excel(name = "目录名称")
    private String name;


}
