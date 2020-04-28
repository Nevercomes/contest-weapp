package com.ruoyi.project.system.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * OSS上传对象 sys_oss_record
 *
 * @author sun
 * @date 2020-04-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOssRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * uuId
     */
    private String id;

    /**
     * url
     */
    @Excel(name = "url")
    private String url;

    /**
     * 文件类型
     */
    @Excel(name = "文件类型")
    private String fileType;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private String businessType;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private String delFlag;


}
