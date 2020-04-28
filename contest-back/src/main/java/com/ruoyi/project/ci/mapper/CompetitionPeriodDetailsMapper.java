package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.CompetitionPeriodDetails;

/**
 * 竞赛详情Mapper接口
 *
 * @author sun
 * @date 2020-04-27
 */
public interface CompetitionPeriodDetailsMapper {
    /**
     * 查询竞赛详情
     *
     * @param cpId 竞赛详情ID
     * @return 竞赛详情
     */
    CompetitionPeriodDetails selectCompetitionPeriodDetailsById(Long cpId);

    /**
     * 查询竞赛详情列表
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 竞赛详情集合
     */
    List<CompetitionPeriodDetails> selectCompetitionPeriodDetailsList(CompetitionPeriodDetails competitionPeriodDetails);

    /**
     * 查询竞赛详情选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectCompetitionPeriodDetailsOptions(CompetitionPeriodDetails competitionPeriodDetails);

    /**
     * 新增竞赛详情
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 结果
     */
    int insertCompetitionPeriodDetails(CompetitionPeriodDetails competitionPeriodDetails);

    /**
     * 修改竞赛详情
     *
     * @param competitionPeriodDetails 竞赛详情
     * @return 结果
     */
    int updateCompetitionPeriodDetails(CompetitionPeriodDetails competitionPeriodDetails);

    /**
     * 删除竞赛详情
     *
     * @param cpId 竞赛详情ID
     * @return 结果
     */
    int deleteCompetitionPeriodDetailsById(Long cpId);

    /**
     * 批量删除竞赛详情
     *
     * @param cpIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCompetitionPeriodDetailsByIds(Long[] cpIds);
}
