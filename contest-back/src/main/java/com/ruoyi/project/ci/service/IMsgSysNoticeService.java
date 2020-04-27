package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.MsgSysNotice;

/**
 * 系统消息Service接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface IMsgSysNoticeService {
    /**
     * 查询系统消息
     *
     * @param id 系统消息ID
     * @return 系统消息
     */
    MsgSysNotice selectMsgSysNoticeById(Long id);

    /**
     * 查询系统消息列表
     *
     * @param msgSysNotice 系统消息
     * @return 系统消息集合
     */
    List<MsgSysNotice> selectMsgSysNoticeList(MsgSysNotice msgSysNotice);

    /**
     * 查询系统消息选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectMsgSysNoticeOptions(MsgSysNotice msgSysNotice);

    /**
     * 新增系统消息
     *
     * @param msgSysNotice 系统消息
     * @return 结果
     */
    int insertMsgSysNotice(MsgSysNotice msgSysNotice);

    /**
     * 修改系统消息
     *
     * @param msgSysNotice 系统消息
     * @return 结果
     */
    int updateMsgSysNotice(MsgSysNotice msgSysNotice);

    /**
     * 批量删除系统消息
     *
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    int deleteMsgSysNoticeByIds(Long[] ids);

    /**
     * 删除系统消息信息
     *
     * @param id 系统消息ID
     * @return 结果
     */
    int deleteMsgSysNoticeById(Long id);
}
