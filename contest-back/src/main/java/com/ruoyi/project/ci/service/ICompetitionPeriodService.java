package com.ruoyi.project.ci.service;

import java.util.List;
import com.ruoyi.project.ci.domain.CompetitionPeriod;

/**
 * 竞赛列表信息Service接口
 *
 * @author sun
 * @date 2020-04-20
 */
public interface ICompetitionPeriodService {
    /**
     * 查询竞赛列表信息
     *
     * @param id 竞赛列表信息ID
     * @return 竞赛列表信息
     */
    CompetitionPeriod selectCompetitionPeriodById(Long id);

    /**
     * 查询竞赛列表信息列表
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 竞赛列表信息集合
     */
    List<CompetitionPeriod> selectCompetitionPeriodList(CompetitionPeriod competitionPeriod);

    /**
     * 新增竞赛列表信息
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 结果
     */
    int insertCompetitionPeriod(CompetitionPeriod competitionPeriod);

    /**
     * 修改竞赛列表信息
     *
     * @param competitionPeriod 竞赛列表信息
     * @return 结果
     */
    int updateCompetitionPeriod(CompetitionPeriod competitionPeriod);

    /**
     * 批量删除竞赛列表信息
     *
     * @param ids 需要删除的竞赛列表信息ID
     * @return 结果
     */
    int deleteCompetitionPeriodByIds(Long[] ids);

    /**
     * 删除竞赛列表信息信息
     *
     * @param id 竞赛列表信息ID
     * @return 结果
     */
    int deleteCompetitionPeriodById(Long id);
}
