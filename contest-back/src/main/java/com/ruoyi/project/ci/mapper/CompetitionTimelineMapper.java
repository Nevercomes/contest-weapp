package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.CompetitionTimeline;

/**
 * 竞赛资讯Mapper接口
 *
 * @author sun
 * @date 2020-08-10
 */
public interface CompetitionTimelineMapper {
    /**
     * 查询竞赛资讯
     *
     * @param id 竞赛资讯ID
     * @return 竞赛资讯
     */
    CompetitionTimeline selectCompetitionTimelineById(Long id);

    /**
     * 查询竞赛资讯列表
     *
     * @param competitionTimeline 竞赛资讯
     * @return 竞赛资讯集合
     */
    List<CompetitionTimeline> selectCompetitionTimelineList(CompetitionTimeline competitionTimeline);

    /**
     * 查询竞赛资讯选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectCompetitionTimelineOptions(CompetitionTimeline competitionTimeline);

    /**
     * 新增竞赛资讯
     *
     * @param competitionTimeline 竞赛资讯
     * @return 结果
     */
    int insertCompetitionTimeline(CompetitionTimeline competitionTimeline);

    /**
     * 修改竞赛资讯
     *
     * @param competitionTimeline 竞赛资讯
     * @return 结果
     */
    int updateCompetitionTimeline(CompetitionTimeline competitionTimeline);

    /**
     * 删除竞赛资讯
     *
     * @param id 竞赛资讯ID
     * @return 结果
     */
    int deleteCompetitionTimelineById(Long id);

    /**
     * 批量删除竞赛资讯
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCompetitionTimelineByIds(Long[] ids);
}
