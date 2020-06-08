package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子内容对象 ci_post_content
 *
 * @author sun
 * @date 2020-06-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 帖子 */
    private Long postId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;


}
