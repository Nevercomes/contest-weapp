package com.ruoyi.project.ci.domain;

import com.ruoyi.project.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

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

    /**
     * 编号
     */
    private Long id;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 发送用户
     */
    @Excel(name = "发送用户")
    private Long sendUserId;
    private String sendUserNickName;
    private String sendUserAvatar;

    /**
     * 接收用户
     */
    @Excel(name = "接收用户")
    private Long receiveUserId;
    private String receiveUserNickName;
    private String receiveUserAvatar;

    /**
     * 是否已读
     */
    @Excel(name = "是否已读")
    private String readFlag;

    /**
     * 摘要部分
     */
    private int notReadNum; // 未读消息的数目


}
