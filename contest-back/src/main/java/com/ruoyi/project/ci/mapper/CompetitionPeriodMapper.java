package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.ci.domain.CompetitionPeriod;
import org.apache.ibatis.annotations.Param;

/**
 * 竞赛列表信息Mapper接口
 *
 * @author sun
 * @date 2020-04-20
 */
public interface CompetitionPeriodMapper {
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
     * 删除竞赛列表信息
     *
     * @param id 竞赛列表信息ID
     * @return 结果
     */
    int deleteCompetitionPeriodById(Long id);

    /**
     * 批量删除竞赛列表信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCompetitionPeriodByIds(Long[] ids);

    /**
     * 更新封面
     * @param cpId
     * @param s
     */
    void updateCover(@Param("id") Long cpId, @Param("url") String s);

    List<Map<String,Object>> selectCompetitionPeriodOptions(CompetitionPeriod competitionPeriod);

    /**
     * 查询关注的竞赛
     * @param competitionPeriod
     * @return
     */
    List<CompetitionPeriod> selectConcernedCompetitionPeriodList(CompetitionPeriod competitionPeriod);

    /**
     * 更新分类标签
     * @param competitionPeriod
     * @return
     */
    int updateClassifyLabelsByInfoId(CompetitionPeriod competitionPeriod);

}
