package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 组队意愿对象 ci_team_expect
 *
 * @author sun
 * @date 2020-04-21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamExpect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Long id;

    /**
     * 比赛
     */
    private Long cpId;
    @Excel(name = "比赛")
    private String cpName;

    /**
     * 分工
     */
    @Excel(name = "分工")
    private String work;

    /**
     * 能力
     */
    @Excel(name = "能力")
    private String capability;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 创建者
     */
    private SysUser createUser;


}
