package com.ruoyi.framework.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.framework.oss.IOssService;
import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.mapper.CompetitionPeriodMapper;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * oss 实现类
 * @author Sunss
 * @since 2020/4/20
 */
@Service
public class OssServiceImpl implements IOssService {

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private CompetitionPeriodMapper competitionPeriodMapper;

    @Override
    public void uploadAvatar(String userName, MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, "avatar/" + file.getOriginalFilename(), new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        userMapper.updateUserAvatar(userName, "https://" + bucketName + "/avatar/" + file.getOriginalFilename());
    }

    @Override
    public void uploadCpCover(Long cpId, MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, "competition/" + file.getOriginalFilename(), new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        competitionPeriodMapper.updateCover(cpId, "https://" + bucketName + "/competition/" + file.getOriginalFilename());
    }
}
