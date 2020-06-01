package com.ruoyi.project.system.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysOssRecordMapper;
import com.ruoyi.project.system.domain.SysOssRecord;
import com.ruoyi.project.system.service.ISysOssRecordService;
import org.springframework.web.multipart.MultipartFile;

/**
 * OSS上传Service业务层处理
 *
 * @author sun
 * @date 2020-04-27
 */
@Service
public class SysOssRecordServiceImpl implements ISysOssRecordService {

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;

    @Autowired
    private SysOssRecordMapper sysOssRecordMapper;

    /**
     * 上传用户头像
     * @param file 头像
     * @return
     * @throws IOException
     */
    @Override
    public String uploadAvatar(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuId = IdUtils.fastSimpleUUID();
        String fileName = FileUtils.getUUIDFileName(uuId, Objects.requireNonNull(file.getOriginalFilename()));
        ossClient.putObject(bucketName, "avatar/" + fileName, new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/avatar/" + fileName;
        insertUploadRecord(uuId, url, OssConstants.BUSINESS_TYPE_AVATAR, fileName.substring(fileName.lastIndexOf(".")));
        return url;
    }

    /**
     * 上传竞赛封面
     * @param file 封面
     * @return
     * @throws IOException
     */
    @Override
    public String uploadCpCover(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuId = IdUtils.fastSimpleUUID();
        String fileName = FileUtils.getUUIDFileName(uuId, Objects.requireNonNull(file.getOriginalFilename()));
        ossClient.putObject(bucketName, "competition/" + fileName, new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/competition/" + fileName;
        insertUploadRecord(uuId, url, OssConstants.BUSINESS_TYPE_COVER, fileName.substring(fileName.lastIndexOf(".")));
        return url;
    }

    /**
     * 上传队伍头像
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadTeamAvatar(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuId = IdUtils.fastSimpleUUID();
        String fileName = FileUtils.getUUIDFileName(uuId, Objects.requireNonNull(file.getOriginalFilename()));
        ossClient.putObject(bucketName, "team/" + fileName, new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/team/" + fileName;
        insertUploadRecord(uuId, url, OssConstants.BUSINESS_TYPE_TEAM, fileName.substring(fileName.lastIndexOf(".")));
        return url;
    }

    /**
     * 上传首页Swiper的图片
     * @param file
     * @return
     */
    @Override
    public String uploadRecoSwiper(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuId = IdUtils.fastSimpleUUID();
        String fileName = FileUtils.getUUIDFileName(uuId, Objects.requireNonNull(file.getOriginalFilename()));
        ossClient.putObject(bucketName, "recoSwiper/" + fileName, new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/recoSwiper/" + fileName;
        insertUploadRecord(uuId, url, OssConstants.BUSINESS_TYPE_TEAM, fileName.substring(fileName.lastIndexOf(".")));
        return url;
    }

    /**
     * 上传首页竞赛推荐图片
     * @param file
     * @return
     */
    @Override
    public String uploadRecoComp(MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String uuId = IdUtils.fastSimpleUUID();
        String fileName = FileUtils.getUUIDFileName(uuId, Objects.requireNonNull(file.getOriginalFilename()));
        ossClient.putObject(bucketName, "recoComp/" + fileName, new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/recoComp/" + fileName;
        insertUploadRecord(uuId, url, OssConstants.BUSINESS_TYPE_TEAM, fileName.substring(fileName.lastIndexOf(".")));
        return url;
    }

    /**
     * 查询OSS上传
     *
     * @param id OSS上传ID
     * @return OSS上传
     */
    @Override
    public SysOssRecord selectSysOssRecordById(String id) {
        return sysOssRecordMapper.selectSysOssRecordById(id);
    }

    /**
     * 查询OSS上传列表
     *
     * @param sysOssRecord OSS上传
     * @return OSS上传
     */
    @Override
    public List<SysOssRecord> selectSysOssRecordList(SysOssRecord sysOssRecord) {
        return sysOssRecordMapper.selectSysOssRecordList(sysOssRecord);
    }

    /**
     * 查询OSS上传选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectSysOssRecordOptions(SysOssRecord sysOssRecord) {
        return sysOssRecordMapper.selectSysOssRecordOptions(sysOssRecord);
    }

    /**
     * 新增OSS上传
     *
     * @param sysOssRecord OSS上传
     * @return 结果
     */
    @Override
    public int insertSysOssRecord(SysOssRecord sysOssRecord) {
        sysOssRecord.preInsert();
        return sysOssRecordMapper.insertSysOssRecord(sysOssRecord);
    }

    /**
     * 修改OSS上传
     *
     * @param sysOssRecord OSS上传
     * @return 结果
     */
    @Override
    public int updateSysOssRecord(SysOssRecord sysOssRecord) {
        sysOssRecord.preUpdate();
        return sysOssRecordMapper.updateSysOssRecord(sysOssRecord);
    }

    /**
     * 批量删除OSS上传
     *
     * @param ids 需要删除的OSS上传ID
     * @return 结果
     */
    @Override
    public int deleteSysOssRecordByIds(String[] ids) {
        return sysOssRecordMapper.deleteSysOssRecordByIds(ids);
    }

    /**
     * 删除OSS上传信息
     *
     * @param id OSS上传ID
     * @return 结果
     */
    @Override
    public int deleteSysOssRecordById(String id) {
        return sysOssRecordMapper.deleteSysOssRecordById(id);
    }

    private void insertUploadRecord(String id, String url, String businessType, String fileType) {
        SysOssRecord ossRecord = new SysOssRecord();
        ossRecord.setId(id);
        ossRecord.setUrl(url);
        ossRecord.setBusinessType(businessType);
        ossRecord.setFileType(fileType);
        this.insertSysOssRecord(ossRecord);
    }

}
