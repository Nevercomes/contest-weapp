package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子草稿对象 ci_post_draft
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostDraft extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 竞赛 */
    private Long cpId;
    @Excel(name = "竞赛")
    private String cpName;

    /** 标题 */
    @Excel(name = "标题")
    private String name;

    /** 关联的帖子 */
    @Excel(name = "关联的帖子")
    private Long associated;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 内容摘要 */
    @Excel(name = "内容摘要")
    private String summary;

    private SysUser createUser;


}
