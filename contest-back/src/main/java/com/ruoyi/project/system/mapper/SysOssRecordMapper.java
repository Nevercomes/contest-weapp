package com.ruoyi.project.system.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.system.domain.SysOssRecord;

/**
 * OSS上传Mapper接口
 *
 * @author sun
 * @date 2020-04-27
 */
public interface SysOssRecordMapper {
    /**
     * 查询OSS上传
     *
     * @param id OSS上传ID
     * @return OSS上传
     */
    SysOssRecord selectSysOssRecordById(String id);

    /**
     * 查询OSS上传列表
     *
     * @param sysOssRecord OSS上传
     * @return OSS上传集合
     */
    List<SysOssRecord> selectSysOssRecordList(SysOssRecord sysOssRecord);

    /**
     * 查询OSS上传选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectSysOssRecordOptions(SysOssRecord sysOssRecord);

    /**
     * 新增OSS上传
     *
     * @param sysOssRecord OSS上传
     * @return 结果
     */
    int insertSysOssRecord(SysOssRecord sysOssRecord);

    /**
     * 修改OSS上传
     *
     * @param sysOssRecord OSS上传
     * @return 结果
     */
    int updateSysOssRecord(SysOssRecord sysOssRecord);

    /**
     * 删除OSS上传
     *
     * @param id OSS上传ID
     * @return 结果
     */
    int deleteSysOssRecordById(String id);

    /**
     * 批量删除OSS上传
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteSysOssRecordByIds(String[] ids);
}
