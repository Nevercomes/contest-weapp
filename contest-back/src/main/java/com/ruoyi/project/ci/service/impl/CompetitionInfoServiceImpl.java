package com.ruoyi.project.ci.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.domain.InfoClassify;
import com.ruoyi.project.ci.mapper.CompetitionClassifyMapper;
import com.ruoyi.project.ci.mapper.CompetitionPeriodMapper;
import com.ruoyi.project.ci.mapper.InfoClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionInfoMapper;
import com.ruoyi.project.ci.domain.CompetitionInfo;
import com.ruoyi.project.ci.service.ICompetitionInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 竞赛信息模板Service业务层处理
 *
 * @author sun
 * @date 2020-04-17
 */
@Service
public class CompetitionInfoServiceImpl implements ICompetitionInfoService {
    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;
    @Autowired
    private InfoClassifyMapper infoClassifyMapper;
    @Autowired
    private CompetitionPeriodMapper competitionPeriodMapper;
    @Autowired
    private CompetitionClassifyMapper competitionClassifyMapper;

    /**
     * 查询竞赛信息模板
     *
     * @param id 竞赛信息模板ID
     * @return 竞赛信息模板
     */
    @Override
    public CompetitionInfo selectCompetitionInfoById(Long id) {
        return competitionInfoMapper.selectCompetitionInfoById(id);
    }

    /**
     * 查询竞赛信息模板列表
     *
     * @param competitionInfo 竞赛信息模板
     * @return 竞赛信息模板
     */
    @Override
    public List<CompetitionInfo> selectCompetitionInfoList(CompetitionInfo competitionInfo) {
        return competitionInfoMapper.selectCompetitionInfoList(competitionInfo);
    }

    /**
     * 新增竞赛信息模板
     *
     * @param competitionInfo 竞赛信息模板
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCompetitionInfo(CompetitionInfo competitionInfo) {
        competitionInfo.preInsert();
        competitionInfoMapper.insertCompetitionInfo(competitionInfo);
        return insertInfoClassify(competitionInfo);
    }

    /**
     * 修改竞赛信息模板
     *
     * @param competitionInfo 竞赛信息模板
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCompetitionInfo(CompetitionInfo competitionInfo) {
        competitionInfo.preUpdate();
        competitionInfoMapper.updateCompetitionInfo(competitionInfo);
        // 删除分类映射
        infoClassifyMapper.deleteInfoClassifyByInfoId(competitionInfo.getId());
        // 批量出入映射
        int res =  insertInfoClassify(competitionInfo);
        // 更新存在的period的分类信息，冗余写入（这一过程异步完成更好）
        List<String> classifyLabelList = competitionClassifyMapper.selectClassifyNameByIds(competitionInfo.getClassifyIds());
        String classifyLabels = String.join(",", classifyLabelList);
        CompetitionPeriod period = new CompetitionPeriod();
        period.setCpInfoId(competitionInfo.getId());
        period.setClassifyLabels(classifyLabels);
        competitionPeriodMapper.updateClassifyLabelsByInfoId(period);
        return res;
    }

    /**
     * 批量删除竞赛信息模板
     *
     * @param ids 需要删除的竞赛信息模板ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionInfoByIds(Long[] ids) {
        return competitionInfoMapper.deleteCompetitionInfoByIds(ids);
    }

    /**
     * 删除竞赛信息模板信息
     *
     * @param id 竞赛信息模板ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionInfoById(Long id) {
        return competitionInfoMapper.deleteCompetitionInfoById(id);
    }

    /**
     * 查询竞赛信息模板选项
     * @param competitionInfo
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCompetitionInfoOptions(CompetitionInfo competitionInfo) {
        return competitionInfoMapper.selectCompetitionInfoOptions(competitionInfo);
    }

    /**
     * 新增信息分类信息
     *
     */
    private int insertInfoClassify(CompetitionInfo info) {
        int rows = 1;
        // 新增分类与竞赛信息模板
        List<InfoClassify> list = new ArrayList<>();
        for (Long cId : info.getClassifyIds()) {
            InfoClassify ic = new InfoClassify();
            ic.setCpInfoId(info.getId());
            ic.setClassifyId(cId);
            list.add(ic);
        }
        if (list.size() > 0) {
            rows = infoClassifyMapper.batchInfoClassify(list);
        }
        return rows;
    }
}
