package com.ruoyi.framework.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据权限
     */
    private String dataScope;

    /**
     * 开始时间
     */
    @JsonIgnore
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonIgnore
    private Date endTime;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 校内浏览模式
     */
    private boolean onlySchool;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    /**
     * 插入前设置通用值
     */
    public void preInsert() {
        if (StringUtils.isEmpty(this.getCreateBy())) {
            this.setCreateBy(SecurityUtils.getUsername());
        }
        this.setCreateTime(new Date());
        this.setUpdateBy(SecurityUtils.getUsername());
        this.setUpdateTime(new Date());
    }

    /**
     * 更新前设置通用值
     */
    public void preUpdate() {
        this.setUpdateBy(SecurityUtils.getUsername());
        this.setUpdateTime(new Date());
    }

    /**
     * 获取校内浏览模式的设置
     */
    public boolean getOnlySchool() {
        return this.onlySchool;
    }
}
