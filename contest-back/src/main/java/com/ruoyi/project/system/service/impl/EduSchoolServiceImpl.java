package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.EduSchoolMapper;
import com.ruoyi.project.system.domain.EduSchool;
import com.ruoyi.project.system.service.IEduSchoolService;

/**
 * 学校信息Service业务层处理
 *
 * @author sun
 * @date 2020-04-17
 */
@Service
public class EduSchoolServiceImpl implements IEduSchoolService {
    @Autowired
    private EduSchoolMapper eduSchoolMapper;

    /**
     * 查询学校信息
     *
     * @param schoolId 学校信息ID
     * @return 学校信息
     */
    @Override
    public EduSchool selectEduSchoolById(Long schoolId) {
        return eduSchoolMapper.selectEduSchoolById(schoolId);
    }

    /**
     * 查询学校信息列表
     *
     * @param eduSchool 学校信息
     * @return 学校信息
     */
    @Override
    public List<EduSchool> selectEduSchoolList(EduSchool eduSchool) {
        return eduSchoolMapper.selectEduSchoolList(eduSchool);
    }

    /**
     * 新增学校信息
     *
     * @param eduSchool 学校信息
     * @return 结果
     */
    @Override
    public int insertEduSchool(EduSchool eduSchool) {
        eduSchool.preInsert();
        return eduSchoolMapper.insertEduSchool(eduSchool);
    }

    /**
     * 修改学校信息
     *
     * @param eduSchool 学校信息
     * @return 结果
     */
    @Override
    public int updateEduSchool(EduSchool eduSchool) {
        eduSchool.preUpdate();
        return eduSchoolMapper.updateEduSchool(eduSchool);
    }

    /**
     * 批量删除学校信息
     *
     * @param schoolIds 需要删除的学校信息ID
     * @return 结果
     */
    @Override
    public int deleteEduSchoolByIds(Long[] schoolIds) {
        return eduSchoolMapper.deleteEduSchoolByIds(schoolIds);
    }

    /**
     * 删除学校信息信息
     *
     * @param schoolId 学校信息ID
     * @return 结果
     */
    @Override
    public int deleteEduSchoolById(Long schoolId) {
        return eduSchoolMapper.deleteEduSchoolById(schoolId);
    }
}
