package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子浏览记录对象 ci_post_view_record
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostViewRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 帖子 */
    private Long postId;
    @Excel(name = "帖子")
    private String postName;


}
