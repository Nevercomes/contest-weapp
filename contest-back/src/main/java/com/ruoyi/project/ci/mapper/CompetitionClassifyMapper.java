package com.ruoyi.project.ci.mapper;

import java.util.List;
import com.ruoyi.project.ci.domain.CompetitionClassify;

/**
 * 竞赛分类Mapper接口
 *
 * @author sun
 * @date 2020-04-17
 */
public interface CompetitionClassifyMapper {
    /**
     * 查询竞赛分类
     *
     * @param id 竞赛分类ID
     * @return 竞赛分类
     */
    CompetitionClassify selectCompetitionClassifyById(Long id);

    /**
     * 查询竞赛分类列表
     *
     * @param competitionClassify 竞赛分类
     * @return 竞赛分类集合
     */
    List<CompetitionClassify> selectCompetitionClassifyList(CompetitionClassify competitionClassify);

    /**
     * 新增竞赛分类
     *
     * @param competitionClassify 竞赛分类
     * @return 结果
     */
    int insertCompetitionClassify(CompetitionClassify competitionClassify);

    /**
     * 修改竞赛分类
     *
     * @param competitionClassify 竞赛分类
     * @return 结果
     */
    int updateCompetitionClassify(CompetitionClassify competitionClassify);

    /**
     * 删除竞赛分类
     *
     * @param id 竞赛分类ID
     * @return 结果
     */
    int deleteCompetitionClassifyById(Long id);

    /**
     * 批量删除竞赛分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCompetitionClassifyByIds(Long[] ids);

    /**
     * 根据竞赛Id查询分类树
     * @param infoId
     * @return
     */
    List<Integer> selectCompetitionClassifyListByInfoId(Long infoId);
}
