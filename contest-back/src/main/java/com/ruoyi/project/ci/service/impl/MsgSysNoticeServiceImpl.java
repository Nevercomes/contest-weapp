package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.MsgSysNoticeMapper;
import com.ruoyi.project.ci.domain.MsgSysNotice;
import com.ruoyi.project.ci.service.IMsgSysNoticeService;

/**
 * 系统消息Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class MsgSysNoticeServiceImpl implements IMsgSysNoticeService {
    @Autowired
    private MsgSysNoticeMapper msgSysNoticeMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询系统消息
     *
     * @param id 系统消息ID
     * @return 系统消息
     */
    @Override
    public MsgSysNotice selectMsgSysNoticeById(Long id) {
        return msgSysNoticeMapper.selectMsgSysNoticeById(id);
    }

    /**
     * 查询系统消息列表
     *
     * @param msgSysNotice 系统消息
     * @return 系统消息
     */
    @Override
    public List<MsgSysNotice> selectMsgSysNoticeList(MsgSysNotice msgSysNotice) {
        return msgSysNoticeMapper.selectMsgSysNoticeList(msgSysNotice);
    }

    /**
     * 查询系统消息选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectMsgSysNoticeOptions(MsgSysNotice msgSysNotice) {
        return msgSysNoticeMapper.selectMsgSysNoticeOptions(msgSysNotice);
    }

    /**
     * 新增系统消息
     *
     * @param msgSysNotice 系统消息
     * @return 结果
     */
    @Override
    public int insertMsgSysNotice(MsgSysNotice msgSysNotice) {
        msgSysNotice.preInsert();
        return msgSysNoticeMapper.insertMsgSysNotice(msgSysNotice);
    }

    /**
     * 修改系统消息
     *
     * @param msgSysNotice 系统消息
     * @return 结果
     */
    @Override
    public int updateMsgSysNotice(MsgSysNotice msgSysNotice) {
        msgSysNotice.preUpdate();
        return msgSysNoticeMapper.updateMsgSysNotice(msgSysNotice);
    }

    /**
     * 批量删除系统消息
     *
     * @param ids 需要删除的系统消息ID
     * @return 结果
     */
    @Override
    public int deleteMsgSysNoticeByIds(Long[] ids) {
        return msgSysNoticeMapper.deleteMsgSysNoticeByIds(ids);
    }

    /**
     * 删除系统消息信息
     *
     * @param id 系统消息ID
     * @return 结果
     */
    @Override
    public int deleteMsgSysNoticeById(Long id) {
        return msgSysNoticeMapper.deleteMsgSysNoticeById(id);
    }

    /**
     * 注入创建者
     * @param msgSysNotice 注入对象
     */
    private void setCreated(MsgSysNotice msgSysNotice) {
        if(msgSysNotice == null) return;
        if (StringUtils.isNotEmpty(msgSysNotice.getCreateBy())) {
//            msgSysNotice.setCreateUser(userMapper.selectCreateUser(msgSysNotice.getCreateBy()));
        }
    }
}
