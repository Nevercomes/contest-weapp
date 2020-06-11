package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 收藏内容对象 ci_collection_content
 *
 * @author sun
 * @date 2020-06-11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 帖子 */
    @Excel(name = "帖子")
    private Long postId;

    /** 目录 */
    @Excel(name = "目录")
    private Long directoryId;


}
