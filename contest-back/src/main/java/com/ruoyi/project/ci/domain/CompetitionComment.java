package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 竞赛评论对象 ci_competition_comment
 *
 * @author sun
 * @date 2020-08-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 竞赛 */
    @Excel(name = "竞赛")
    private Long cpId;

    private String cpName;

    private SysUser createUser;


}
