package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 私信管理对象 ci_msg_letter
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgLetter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 接收用户 */
    @Excel(name = "接收用户")
    private Long receiveUser;

    /** 是否已读 */
    @Excel(name = "是否已读")
    private String readFlag;


}
