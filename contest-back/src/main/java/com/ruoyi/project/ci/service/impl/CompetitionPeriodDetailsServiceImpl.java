package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionPeriodDetailsMapper;
import com.ruoyi.project.ci.domain.CompetitionPeriodDetails;
import com.ruoyi.project.ci.service.ICompetitionPeriodDetailsService;

/**
 * 竞赛详情Service业务层处理
 *
 * @author sun
 * @date 2020-04-27
 */
@Service
public class CompetitionPeriodDetailsServiceImpl implements ICompetitionPeriodDetailsService {
    @Autowired
    private CompetitionPeriodDetailsMapper competitionPeriodDetailsMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询竞赛详情
     *
     * @param cpId 竞赛详情ID
     * @return 竞赛详情
     */
    @Override
    public CompetitionPeriodDetails selectCompetitionPeriodDetailsById(Long cpId) {
        return competitionPeriodDetailsMapper.selectCompetitionPeriodDetailsById(cpId);
    }

    /**
     * 查询竞赛详情列表
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 竞赛详情
     */
    @Override
    public List<CompetitionPeriodDetails> selectCompetitionPeriodDetailsList(CompetitionPeriodDetails competitionPeriodDetails) {
        return competitionPeriodDetailsMapper.selectCompetitionPeriodDetailsList(competitionPeriodDetails);
    }

    /**
     * 查询竞赛详情选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectCompetitionPeriodDetailsOptions(CompetitionPeriodDetails competitionPeriodDetails) {
        return competitionPeriodDetailsMapper.selectCompetitionPeriodDetailsOptions(competitionPeriodDetails);
    }

    /**
     * 新增竞赛详情
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 结果
     */
    @Override
    public int insertCompetitionPeriodDetails(CompetitionPeriodDetails competitionPeriodDetails) {
        competitionPeriodDetails.preInsert();
        return competitionPeriodDetailsMapper.insertCompetitionPeriodDetails(competitionPeriodDetails);
    }

    /**
     * 修改竞赛详情
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 结果
     */
    @Override
    public int updateCompetitionPeriodDetails(CompetitionPeriodDetails competitionPeriodDetails) {
        competitionPeriodDetails.preUpdate();
        return competitionPeriodDetailsMapper.updateCompetitionPeriodDetails(competitionPeriodDetails);
    }

    /**
     * 批量删除竞赛详情
     *
     * @param cpIds 需要删除的竞赛详情ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionPeriodDetailsByIds(Long[] cpIds) {
        return competitionPeriodDetailsMapper.deleteCompetitionPeriodDetailsByIds(cpIds);
    }

    /**
     * 删除竞赛详情信息
     *
     * @param cpId 竞赛详情ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionPeriodDetailsById(Long cpId) {
        return competitionPeriodDetailsMapper.deleteCompetitionPeriodDetailsById(cpId);
    }

    /**
     * 注入创建者
     * @param competitionPeriodDetails 注入对象
     */
    private void setCreated(CompetitionPeriodDetails competitionPeriodDetails) {
        if(competitionPeriodDetails == null) return;
        if (StringUtils.isNotEmpty(competitionPeriodDetails.getCreateBy())) {
           // competitionPeriodDetails.setCreateUser(userMapper.selectCreateUser(competitionPeriodDetails.getCreateBy()));
        }
    }
}
