package com.ruoyi.project.system.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysUserExtMapper;
import com.ruoyi.project.system.domain.SysUserExt;
import com.ruoyi.project.system.service.ISysUserExtService;

/**
 * 用户扩展信息Service业务层处理
 *
 * @author sun
 * @date 2020-05-21
 */
@Service
public class SysUserExtServiceImpl implements ISysUserExtService {
    @Autowired
    private SysUserExtMapper sysUserExtMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询用户扩展信息
     *
     * @param userId 用户扩展信息ID
     * @return 用户扩展信息
     */
    @Override
    public SysUserExt selectSysUserExtById(Long userId) {
        return sysUserExtMapper.selectSysUserExtById(userId);
    }

    /**
     * 查询用户扩展信息列表
     *
     * @param sysUserExt 用户扩展信息
     * @return 用户扩展信息
     */
    @Override
    public List<SysUserExt> selectSysUserExtList(SysUserExt sysUserExt) {
        return sysUserExtMapper.selectSysUserExtList(sysUserExt);
    }

    /**
     * 查询用户扩展信息选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectSysUserExtOptions(SysUserExt sysUserExt) {
        return sysUserExtMapper.selectSysUserExtOptions(sysUserExt);
    }

    /**
     * 新增用户扩展信息
     *
     * @param sysUserExt 用户扩展信息
     * @return 结果
     */
    @Override
    public int insertSysUserExt(SysUserExt sysUserExt) {
        sysUserExt.preInsert();
        return sysUserExtMapper.insertSysUserExt(sysUserExt);
    }

    /**
     * 修改用户扩展信息
     *
     * @param sysUserExt 用户扩展信息
     * @return 结果
     */
    @Override
    public int updateSysUserExt(SysUserExt sysUserExt) {
        sysUserExt.preUpdate();
        return sysUserExtMapper.updateSysUserExt(sysUserExt);
    }

    /**
     * 批量删除用户扩展信息
     *
     * @param userIds 需要删除的用户扩展信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserExtByIds(Long[] userIds) {
        return sysUserExtMapper.deleteSysUserExtByIds(userIds);
    }

    /**
     * 删除用户扩展信息信息
     *
     * @param userId 用户扩展信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserExtById(Long userId) {
        return sysUserExtMapper.deleteSysUserExtById(userId);
    }

    /**
     * 注入创建者
     * @param sysUserExt 注入对象
     */
    private void setCreated(SysUserExt sysUserExt) {
        if(sysUserExt == null) return;
        if (StringUtils.isNotEmpty(sysUserExt.getCreateBy())) {
           // sysUserExt.setCreateUser(userMapper.selectCreateUser(sysUserExt.getCreateBy()));
        }
    }
}
