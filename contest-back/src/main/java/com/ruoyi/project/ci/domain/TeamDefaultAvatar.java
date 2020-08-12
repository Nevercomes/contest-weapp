package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 默认团队头像对象 ci_team_default_avatar
 *
 * @author sun
 * @date 2020-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamDefaultAvatar extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String picUrl;


}
