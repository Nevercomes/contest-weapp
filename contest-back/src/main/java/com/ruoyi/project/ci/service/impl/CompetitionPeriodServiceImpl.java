package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionPeriodMapper;
import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.service.ICompetitionPeriodService;

/**
 * 竞赛列表信息Service业务层处理
 *
 * @author sun
 * @date 2020-04-20
 */
@Service
public class CompetitionPeriodServiceImpl implements ICompetitionPeriodService {
    @Autowired
    private CompetitionPeriodMapper competitionPeriodMapper;

    /**
     * 查询竞赛列表信息
     *
     * @param id 竞赛列表信息ID
     * @return 竞赛列表信息
     */
    @Override
    public CompetitionPeriod selectCompetitionPeriodById(Long id) {
        return competitionPeriodMapper.selectCompetitionPeriodById(id);
    }

    /**
     * 查询竞赛列表信息列表
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 竞赛列表信息
     */
    @Override
    public List<CompetitionPeriod> selectCompetitionPeriodList(CompetitionPeriod competitionPeriod) {
        return competitionPeriodMapper.selectCompetitionPeriodList(competitionPeriod);
    }

    /**
     * 新增竞赛列表信息
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 结果
     */
    @Override
    public int insertCompetitionPeriod(CompetitionPeriod competitionPeriod) {
        competitionPeriod.preInsert();
        return competitionPeriodMapper.insertCompetitionPeriod(competitionPeriod);
    }

    /**
     * 修改竞赛列表信息
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 结果
     */
    @Override
    public int updateCompetitionPeriod(CompetitionPeriod competitionPeriod) {
        competitionPeriod.preUpdate();
        return competitionPeriodMapper.updateCompetitionPeriod(competitionPeriod);
    }

    /**
     * 批量删除竞赛列表信息
     *
     * @param ids 需要删除的竞赛列表信息ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionPeriodByIds(Long[] ids) {
        return competitionPeriodMapper.deleteCompetitionPeriodByIds(ids);
    }

    /**
     * 删除竞赛列表信息信息
     *
     * @param id 竞赛列表信息ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionPeriodById(Long id) {
        return competitionPeriodMapper.deleteCompetitionPeriodById(id);
    }

    @Override
    public List<Map<String, Object>> selectCompetitionPeriodOptions(CompetitionPeriod competitionPeriod) {
        return competitionPeriodMapper.selectCompetitionPeriodOptions(competitionPeriod);
    }
}
