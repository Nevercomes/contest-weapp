package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.MsgLetter;

/**
 * 私信管理Service接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface IMsgLetterService {
    /**
     * 查询私信管理
     *
     * @param id 私信管理ID
     * @return 私信管理
     */
    MsgLetter selectMsgLetterById(Long id);

    /**
     * 查询私信管理列表
     *
     * @param msgLetter 私信管理
     * @return 私信管理集合
     */
    List<MsgLetter> selectMsgLetterList(MsgLetter msgLetter);

    /**
     * 查询私信管理选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectMsgLetterOptions(MsgLetter msgLetter);

    /**
     * 新增私信管理
     *
     * @param msgLetter 私信管理
     * @return 结果
     */
    MsgLetter insertMsgLetter(MsgLetter msgLetter);

    /**
     * 修改私信管理
     *
     * @param msgLetter 私信管理
     * @return 结果
     */
    int updateMsgLetter(MsgLetter msgLetter);

    /**
     * 批量删除私信管理
     *
     * @param ids 需要删除的私信管理ID
     * @return 结果
     */
    int deleteMsgLetterByIds(Long[] ids);

    /**
     * 删除私信管理信息
     *
     * @param id 私信管理ID
     * @return 结果
     */
    int deleteMsgLetterById(Long id);

    /**
     * 查询消息列表摘要
     * @param msgLetter
     * @return
     */
    List<MsgLetter> selectLetterDigest(MsgLetter msgLetter);

    /**
     * 查询聊天内容
     * @param msgLetter
     * @return
     */
    List<MsgLetter> selectLetterContent(MsgLetter msgLetter);

    /**
     * 设置已读
     * @param msgLetter
     * @return
     */
    int readMsgLetter(MsgLetter msgLetter);
}
