package com.ruoyi.project.ci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 竞赛资讯对象 ci_competition_timeline
 *
 * @author sun
 * @date 2020-08-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompetitionTimeline extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 竞赛Id */
    @Excel(name = "竞赛")
    private Long cpId;

    private String cpName;

    /** 时间 */
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;

    /** 事件 */
    @Excel(name = "事件")
    private String content;

    /** 外部链接 */
    @Excel(name = "外部链接")
    private String outerLink;


}
