package com.ruoyi.project.ci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 竞赛经历对象 ci_user_experience
 *
 * @author sun
 * @date 2020-04-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserExperience extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long id;

    /** 比赛时间 */
    @Excel(name = "比赛时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /** 比赛名称 */
    @Excel(name = "比赛名称")
    private String name;

    /** 奖项 */
    @Excel(name = "奖项")
    private String awards;

    /** 对内职务 */
    @Excel(name = "对内职务")
    private String work;

    private SysUser createUser;

}
