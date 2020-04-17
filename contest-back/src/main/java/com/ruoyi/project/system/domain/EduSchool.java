package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学校信息对象 edu_school
 *
 * @author sun
 * @date 2020-04-17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EduSchool extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 学校ID，主键，唯一，递增 */
    private Long schoolId;

    /** 学校编号 */
    @Excel(name = "学校编号")
    private String schoolNo;

    /** 学校名字全称 */
    @Excel(name = "学校名字全称")
    private String schoolName;

    /** 学校名字简称 */
    @Excel(name = "学校名字简称")
    private String shortName;

    /** 英文全称 */
    @Excel(name = "英文全称")
    private String englishName;

    /** 缩写英文名称 */
    @Excel(name = "缩写英文名称")
    private String englishShortName;

    /** 学校名称首字母，支持多个 */
    @Excel(name = "学校名称首字母，支持多个")
    private String schoolFirst;

    /** 学校全称拼音首字母 */
    @Excel(name = "学校全称拼音首字母")
    private String schoolFirstPy;

    /** 学校全称拼音 */
    @Excel(name = "学校全称拼音")
    private String schoolPinyin;

    /** 学历 */
    @Excel(name = "学历")
    private String schoolDegreeType;


}
