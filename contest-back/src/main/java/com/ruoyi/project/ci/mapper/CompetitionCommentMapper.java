package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.CompetitionComment;

/**
 * 竞赛评论Mapper接口
 *
 * @author sun
 * @date 2020-08-09
 */
public interface CompetitionCommentMapper {
    /**
     * 查询竞赛评论
     *
     * @param id 竞赛评论ID
     * @return 竞赛评论
     */
    CompetitionComment selectCompetitionCommentById(Long id);

    /**
     * 查询竞赛评论列表
     *
     * @param competitionComment 竞赛评论
     * @return 竞赛评论集合
     */
    List<CompetitionComment> selectCompetitionCommentList(CompetitionComment competitionComment);

    /**
     * 查询竞赛评论选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectCompetitionCommentOptions(CompetitionComment competitionComment);

    /**
     * 新增竞赛评论
     *
     * @param competitionComment 竞赛评论
     * @return 结果
     */
    int insertCompetitionComment(CompetitionComment competitionComment);

    /**
     * 修改竞赛评论
     *
     * @param competitionComment 竞赛评论
     * @return 结果
     */
    int updateCompetitionComment(CompetitionComment competitionComment);

    /**
     * 删除竞赛评论
     *
     * @param id 竞赛评论ID
     * @return 结果
     */
    int deleteCompetitionCommentById(Long id);

    /**
     * 批量删除竞赛评论
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCompetitionCommentByIds(Long[] ids);
}
