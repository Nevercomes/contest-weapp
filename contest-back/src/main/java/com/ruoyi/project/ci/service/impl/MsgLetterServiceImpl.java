package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
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
    public int insertMsgLetter(MsgLetter msgLetter) {
        msgLetter.preInsert();
        return msgLetterMapper.insertMsgLetter(msgLetter);
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
