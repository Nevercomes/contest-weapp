package com.ruoyi.project.ci.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 系统消息对象 ci_msg_sys_notice
 *
 * @author sun
 * @date 2020-04-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgSysNotice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 跳转路径 */
    @Excel(name = "跳转路径")
    private String url;

    /** 目标用户 */
    @Excel(name = "目标用户")
    private Long toUserId;


}
