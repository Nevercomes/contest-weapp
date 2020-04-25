package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 关注竞赛对象 ci_user_concern_cp
 *
 * @author sun
 * @date 2020-04-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserConcernCp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 竞赛 */
    private Long cpId;
    @Excel(name = "竞赛")
    private String cpName;


}
