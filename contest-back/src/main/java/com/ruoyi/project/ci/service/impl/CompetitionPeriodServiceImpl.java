package com.ruoyi.project.ci.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.ci.domain.CompetitionInfo;
import com.ruoyi.project.ci.domain.CompetitionPeriodDetails;
import com.ruoyi.project.ci.domain.InfoClassify;
import com.ruoyi.project.ci.mapper.CompetitionClassifyMapper;
import com.ruoyi.project.ci.mapper.CompetitionInfoMapper;
import com.ruoyi.project.ci.mapper.InfoClassifyMapper;
import com.ruoyi.project.ci.service.ICompetitionPeriodDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionPeriodMapper;
import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.service.ICompetitionPeriodService;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ICompetitionPeriodDetailsService competitionPeriodDetailsService;
    @Autowired
    private InfoClassifyMapper infoClassifyMapper;
    @Autowired
    private CompetitionClassifyMapper competitionClassifyMapper;

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
    @Transactional
    public int insertCompetitionPeriod(CompetitionPeriod competitionPeriod) {
        competitionPeriod.preInsert();
        // 获取分类标签数据
        InfoClassify query = new InfoClassify();
        query.setCpInfoId(competitionPeriod.getCpInfoId());
        List<InfoClassify> list = infoClassifyMapper.selectInfoClassifyList(query);
        Long[] ids = list.stream().map(InfoClassify::getClassifyId).toArray(Long[]::new);
        List<String> labelList = competitionClassifyMapper.selectClassifyNameByIds(ids);
        String labels = String.join(",", labelList);
        competitionPeriod.setClassifyLabels(labels);
        int res = competitionPeriodMapper.insertCompetitionPeriod(competitionPeriod);
        CompetitionPeriodDetails details = competitionPeriod.getDetails();
        if (details != null) {
            details.setCpId(competitionPeriod.getId());
            competitionPeriodDetailsService.insertCompetitionPeriodDetails(details);
        }
        return res;
    }

    /**
     * 修改竞赛列表信息
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCompetitionPeriod(CompetitionPeriod competitionPeriod) {
        competitionPeriod.preUpdate();
        int res = competitionPeriodMapper.updateCompetitionPeriod(competitionPeriod);
        CompetitionPeriodDetails details = competitionPeriod.getDetails();
        if (details != null) {
            if (details.getCpId() != null) {
                competitionPeriodDetailsService.updateCompetitionPeriodDetails(details);
            } else {
                details.setCpId(competitionPeriod.getId());
                competitionPeriodDetailsService.insertCompetitionPeriodDetails(details);
            }
        }
        return res;
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

    @Override
    public List<CompetitionPeriod> selectConcernedCompetitionPeriodList(CompetitionPeriod competitionPeriod) {
        return competitionPeriodMapper.selectConcernedCompetitionPeriodList(competitionPeriod);
    }
}
