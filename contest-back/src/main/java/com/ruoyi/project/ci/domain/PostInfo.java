package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子信息对象 ci_post_info
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 删除标记 */
    private String delFlag;

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
    private PostInfo assPost;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 内容摘要 */
    @Excel(name = "内容摘要")
    private String summary;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeNum;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long collectNum;

    /** 浏览数 */
    @Excel(name = "浏览数")
    private Long viewNum;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long commentNum;

    /** 下载数 */
    @Excel(name = "下载数")
    private Long downloadNum;

    private SysUser createUser;
}
