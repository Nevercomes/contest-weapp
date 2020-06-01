package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.RecommendCompetition;

/**
 * 竞赛推荐Mapper接口
 *
 * @author sun
 * @date 2020-05-31
 */
public interface RecommendCompetitionMapper {
    /**
     * 查询竞赛推荐
     *
     * @param id 竞赛推荐ID
     * @return 竞赛推荐
     */
    RecommendCompetition selectRecommendCompetitionById(Long id);

    /**
     * 查询竞赛推荐列表
     *
     * @param recommendCompetition 竞赛推荐
     * @return 竞赛推荐集合
     */
    List<RecommendCompetition> selectRecommendCompetitionList(RecommendCompetition recommendCompetition);

    /**
     * 查询竞赛推荐选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectRecommendCompetitionOptions(RecommendCompetition recommendCompetition);

    /**
     * 新增竞赛推荐
     *
     * @param recommendCompetition 竞赛推荐
     * @return 结果
     */
    int insertRecommendCompetition(RecommendCompetition recommendCompetition);

    /**
     * 修改竞赛推荐
     *
     * @param recommendCompetition 竞赛推荐
     * @return 结果
     */
    int updateRecommendCompetition(RecommendCompetition recommendCompetition);

    /**
     * 删除竞赛推荐
     *
     * @param id 竞赛推荐ID
     * @return 结果
     */
    int deleteRecommendCompetitionById(Long id);

    /**
     * 批量删除竞赛推荐
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRecommendCompetitionByIds(Long[] ids);
}
