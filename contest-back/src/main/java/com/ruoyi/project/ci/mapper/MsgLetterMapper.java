package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.MsgLetter;
import org.apache.ibatis.annotations.Param;

/**
 * 私信管理Mapper接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface MsgLetterMapper {
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
    int insertMsgLetter(MsgLetter msgLetter);

    /**
     * 修改私信管理
     *
     * @param msgLetter 私信管理
     * @return 结果
     */
    int updateMsgLetter(MsgLetter msgLetter);

    /**
     * 删除私信管理
     *
     * @param id 私信管理ID
     * @return 结果
     */
    int deleteMsgLetterById(Long id);

    /**
     * 批量删除私信管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteMsgLetterByIds(Long[] ids);

    /**
     * 查询消息摘要列表用户
     * @param msgLetter
     * @return
     */
    List<MsgLetter> selectLetterDigestUser(MsgLetter msgLetter);

    /**
     * 查询最新的消息
     * @param sendUserId
     * @param receiveUserId
     * @return
     */
    MsgLetter selectLastMsg(@Param("sendUserId") Long sendUserId, @Param("receiveUserId") Long receiveUserId);

    /**
     * 查询用户的未读消息
     * @param sendUserId
     * @param receiveUserId
     * @return
     */
    MsgLetter selectNotReadNum(@Param("sendUserId") Long sendUserId, @Param("receiveUserId") Long receiveUserId);

    /**
     * 查询消息内容
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
