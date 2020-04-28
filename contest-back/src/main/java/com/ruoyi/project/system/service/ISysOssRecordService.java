package com.ruoyi.project.system.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.ruoyi.project.system.domain.SysOssRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * OSS上传Service接口
 *
 * @author sun
 * @date 2020-04-27
 */
public interface ISysOssRecordService {

    /**
     * 上传用户头像
     * @param file 头像
     * @return url地址
     */
    String uploadAvatar(MultipartFile file) throws IOException;

    /**
     * 上传竞赛封面
     * @param file 封面
     * @return url地址
     */
    String uploadCpCover(MultipartFile file) throws IOException;

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
     * 批量删除OSS上传
     *
     * @param ids 需要删除的OSS上传ID
     * @return 结果
     */
    int deleteSysOssRecordByIds(String[] ids);

    /**
     * 删除OSS上传信息
     *
     * @param id OSS上传ID
     * @return 结果
     */
    int deleteSysOssRecordById(String id);
}
