package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帖子推荐对象 ci_recommend_post
 *
 * @author sun
 * @date 2020-05-31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendPost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 图片Url */
    @Excel(name = "图片Url")
    private String picUrl;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 帖子 */
    @Excel(name = "帖子")
    private Long postId;


}
