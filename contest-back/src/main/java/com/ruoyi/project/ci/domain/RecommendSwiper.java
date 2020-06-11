package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 首页推荐对象 ci_recommend_swiper
 *
 * @author sun
 * @date 2020-05-31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendSwiper extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 图片Url */
    @Excel(name = "图片Url")
    private String picUrl;

    /** 跳转地址 */
    @Excel(name = "跳转地址")
    private String link;

    /**
     * 关联竞赛
     */
    private Long cpId;

    /**
     * 关联帖子
     */
    private Long postId;

    /** 推荐类型 */
    @Excel(name = "推荐类型")
    private String type;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;


}
