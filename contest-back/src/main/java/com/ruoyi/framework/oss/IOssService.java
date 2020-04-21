package com.ruoyi.framework.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Sunss
 * @since 2020/4/20
 */
public interface IOssService {

    /**
     * 上传用户头像
     * @param userName
     * @param file
     */
    void uploadAvatar(String userName, MultipartFile file) throws IOException;

    /**
     * 上传竞赛封面
     * @param cpId
     * @param file
     */
    void uploadCpCover(Long cpId, MultipartFile file) throws IOException;

}
