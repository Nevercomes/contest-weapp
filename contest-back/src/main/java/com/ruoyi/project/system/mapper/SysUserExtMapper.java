package com.ruoyi.project.system.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.system.domain.SysUserExt;

/**
 * 用户扩展信息Mapper接口
 *
 * @author sun
 * @date 2020-05-21
 */
public interface SysUserExtMapper {
    /**
     * 查询用户扩展信息
     *
     * @param userId 用户扩展信息ID
     * @return 用户扩展信息
     */
    SysUserExt selectSysUserExtById(Long userId);

    /**
     * 查询用户扩展信息列表
     *
     * @param sysUserExt 用户扩展信息
     * @return 用户扩展信息集合
     */
    List<SysUserExt> selectSysUserExtList(SysUserExt sysUserExt);

    /**
     * 查询用户扩展信息选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectSysUserExtOptions(SysUserExt sysUserExt);

    /**
     * 新增用户扩展信息
     *
     * @param sysUserExt 用户扩展信息
     * @return 结果
     */
    int insertSysUserExt(SysUserExt sysUserExt);

    /**
     * 修改用户扩展信息
     *
     * @param sysUserExt 用户扩展信息
     * @return 结果
     */
    int updateSysUserExt(SysUserExt sysUserExt);

    /**
     * 删除用户扩展信息
     *
     * @param userId 用户扩展信息ID
     * @return 结果
     */
    int deleteSysUserExtById(Long userId);

    /**
     * 批量删除用户扩展信息
     *
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    int deleteSysUserExtByIds(Long[] userIds);
}
