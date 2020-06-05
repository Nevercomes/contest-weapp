package com.ruoyi.project.ci.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.MsgLetterMapper;
import com.ruoyi.project.ci.domain.MsgLetter;
import com.ruoyi.project.ci.service.IMsgLetterService;

/**
 * 私信管理Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class MsgLetterServiceImpl implements IMsgLetterService {
    @Autowired
    private MsgLetterMapper msgLetterMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询私信管理
     *
     * @param id 私信管理ID
     * @return 私信管理
     */
    @Override
    public MsgLetter selectMsgLetterById(Long id) {
        return msgLetterMapper.selectMsgLetterById(id);
    }

    /**
     * 查询私信管理列表
     *
     * @param msgLetter 私信管理
     * @return 私信管理
     */
    @Override
    public List<MsgLetter> selectMsgLetterList(MsgLetter msgLetter) {
        return msgLetterMapper.selectMsgLetterList(msgLetter);
    }

    /**
     * 查询私信管理选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectMsgLetterOptions(MsgLetter msgLetter) {
        return msgLetterMapper.selectMsgLetterOptions(msgLetter);
    }

    /**
     * 新增私信管理
     *
     * @param msgLetter 私信管理
     * @return 结果
     */
    @Override
    public MsgLetter insertMsgLetter(MsgLetter msgLetter) {
        msgLetter.preInsert();
        int res = msgLetterMapper.insertMsgLetter(msgLetter);
        msgLetter.setSendUserAvatar(SecurityUtils.getLoginUser().getUser().getAvatar());
        msgLetter.setSendUserNickName(SecurityUtils.getLoginUser().getUser().getNickName());
        return msgLetter;
    }

    /**
     * 修改私信管理
     *
     * @param msgLetter 私信管理
     * @return 结果
     */
    @Override
    public int updateMsgLetter(MsgLetter msgLetter) {
        msgLetter.preUpdate();
        return msgLetterMapper.updateMsgLetter(msgLetter);
    }

    /**
     * 批量删除私信管理
     *
     * @param ids 需要删除的私信管理ID
     * @return 结果
     */
    @Override
    public int deleteMsgLetterByIds(Long[] ids) {
        return msgLetterMapper.deleteMsgLetterByIds(ids);
    }

    /**
     * 删除私信管理信息
     *
     * @param id 私信管理ID
     * @return 结果
     */
    @Override
    public int deleteMsgLetterById(Long id) {
        return msgLetterMapper.deleteMsgLetterById(id);
    }

    /**
     * 查询消息摘要列表
     * @param msgLetter
     * @return
     */
    @Override
    public List<MsgLetter> selectLetterDigest(MsgLetter msgLetter) {
        Long userId = SecurityUtils.getUserId();
        List<MsgLetter> msgList = msgLetterMapper.selectLetterDigestUser(msgLetter);
        // 获取到所有有消息的用户
        List<Long> userIdList = new ArrayList<>();
        for (MsgLetter msg : msgList) {
            if (userId.equals(msg.getSendUserId())) {
                if (!userIdList.contains(msg.getReceiveUserId())) {
                    userIdList.add(msg.getReceiveUserId());
                }
            } else if (userId.equals(msg.getReceiveUserId())) {
                if (!userIdList.contains(msg.getSendUserId())) {
                    userIdList.add(msg.getSendUserId());
                }
            }
        }
        // 根据用户单独查询最新的一条消息和未读数目
        List<MsgLetter> list = new ArrayList<>();
        for (Long rId : userIdList) {
            // 用户信息展示在receive处,选出的receiveId不是真实的交流的用户，有可能是自己
            MsgLetter last = msgLetterMapper.selectLastMsg(userId, rId);
            SysUser user = userMapper.selectUserById(rId);
            last.setReceiveUserId(rId);
            last.setReceiveUserAvatar(user.getAvatar());
            last.setReceiveUserNickName(user.getNickName());
            MsgLetter notRead = msgLetterMapper.selectNotReadNum(rId, userId);
            last.setNotReadNum(notRead == null ? 0 : notRead.getNotReadNum());
            list.add(last);
        }
        return list;
    }

    /**
     * 查询消息内容
     * @param msgLetter
     * @return
     */
    @Override
    public List<MsgLetter> selectLetterContent(MsgLetter msgLetter) {
        return msgLetterMapper.selectLetterContent(msgLetter);
    }

    /**
     * 设置已读
     * @param msgLetter
     * @return
     */
    @Override
    public int readMsgLetter(MsgLetter msgLetter) {
        return msgLetterMapper.readMsgLetter(msgLetter);
    }

    /**
     * 注入创建者
     * @param msgLetter 注入对象
     */
    private void setCreated(MsgLetter msgLetter) {
        if(msgLetter == null) return;
        if (StringUtils.isNotEmpty(msgLetter.getCreateBy())) {
//            msgLetter.setCreateUser(userMapper.selectCreateUser(msgLetter.getCreateBy()));
        }
    }
}
