package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 竞赛分类对象 ci_competition_classify
 *
 * @author sun
 * @date 2020-04-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionClassify extends TreeEntity{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String name;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer sort;

    private List<CompetitionClassify> childrens = new ArrayList<>();

}
