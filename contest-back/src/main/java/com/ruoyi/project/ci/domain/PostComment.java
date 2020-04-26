package com.ruoyi.project.ci.domain;

import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子评论对象 ci_post_comment
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 帖子 */
    private Long postId;
    @Excel(name = "帖子")
    private String postName;

    /** 删除标记 */
    private String delFlag;

    private SysUser createUser;


}
