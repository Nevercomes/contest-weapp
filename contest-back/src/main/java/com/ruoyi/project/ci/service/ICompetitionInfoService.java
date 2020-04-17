package com.ruoyi.project.ci.service;

import java.util.List;
import com.ruoyi.project.ci.domain.CompetitionInfo;

/**
 * 竞赛信息模板Service接口
 *
 * @author sun
 * @date 2020-04-17
 */
public interface ICompetitionInfoService {
    /**
     * 查询竞赛信息模板
     *
     * @param id 竞赛信息模板ID
     * @return 竞赛信息模板
     */
    CompetitionInfo selectCompetitionInfoById(Long id);

    /**
     * 查询竞赛信息模板列表
     *
     * @param competitionInfo 竞赛信息模板
     * @return 竞赛信息模板集合
     */
    List<CompetitionInfo> selectCompetitionInfoList(CompetitionInfo competitionInfo);

    /**
     * 新增竞赛信息模板
     *
     * @param competitionInfo 竞赛信息模板
     * @return 结果
     */
    int insertCompetitionInfo(CompetitionInfo competitionInfo);

    /**
     * 修改竞赛信息模板
     *
     * @param competitionInfo 竞赛信息模板
     * @return 结果
     */
    int updateCompetitionInfo(CompetitionInfo competitionInfo);

    /**
     * 批量删除竞赛信息模板
     *
     * @param ids 需要删除的竞赛信息模板ID
     * @return 结果
     */
    int deleteCompetitionInfoByIds(Long[] ids);

    /**
     * 删除竞赛信息模板信息
     *
     * @param id 竞赛信息模板ID
     * @return 结果
     */
    int deleteCompetitionInfoById(Long id);
}
