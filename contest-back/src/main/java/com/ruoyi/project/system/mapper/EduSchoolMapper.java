package com.ruoyi.project.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.system.domain.EduSchool;
import org.apache.ibatis.annotations.Param;

/**
 * 学校信息Mapper接口
 *
 * @author sun
 * @date 2020-04-17
 */
public interface EduSchoolMapper {
    /**
     * 查询学校信息
     *
     * @param schoolId 学校信息ID
     * @return 学校信息
     */
    EduSchool selectEduSchoolById(Long schoolId);

    /**
     * 查询学校信息列表
     *
     * @param eduSchool 学校信息
     * @return 学校信息集合
     */
    List<EduSchool> selectEduSchoolList(EduSchool eduSchool);

    /**
     * 新增学校信息
     *
     * @param eduSchool 学校信息
     * @return 结果
     */
    int insertEduSchool(EduSchool eduSchool);

    /**
     * 修改学校信息
     *
     * @param eduSchool 学校信息
     * @return 结果
     */
    int updateEduSchool(EduSchool eduSchool);

    /**
     * 删除学校信息
     *
     * @param schoolId 学校信息ID
     * @return 结果
     */
    int deleteEduSchoolById(Long schoolId);

    /**
     * 批量删除学校信息
     *
     * @param schoolIds 需要删除的数据ID
     * @return 结果
     */
    int deleteEduSchoolByIds(Long[] schoolIds);

    /**
     * 查询学校信息选项
     * @param eduSchool
     * @return
     */
    List<Map<String,Object>> selectEduSchoolOptions(EduSchool eduSchool);
}
