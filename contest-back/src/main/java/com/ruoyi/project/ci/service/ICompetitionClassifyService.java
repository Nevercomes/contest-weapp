package com.ruoyi.project.ci.service;

import java.util.List;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.ci.domain.CompetitionClassify;

/**
 * 竞赛分类Service接口
 *
 * @author sun
 * @date 2020-04-17
 */
public interface ICompetitionClassifyService {
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
     * 批量删除竞赛分类
     *
     * @param ids 需要删除的竞赛分类ID
     * @return 结果
     */
    int deleteCompetitionClassifyByIds(Long[] ids);

    /**
     * 删除竞赛分类信息
     *
     * @param id 竞赛分类ID
     * @return 结果
     */
    int deleteCompetitionClassifyById(Long id);

    /**
     * 构建前端所需的竞赛分类树
     * @param list
     * @return
     */
    List<TreeSelect> buildCompetitionClassifyTreeSelect(List<CompetitionClassify> list);

    /**
     * 根据竞赛信息Id查询分类树结构
     * @param infoId
     * @return
     */
    List<Integer> selectCompetitionClassifyListByInfoId(Long infoId);

    /**
     * 根据id选取分类名称
     * @param classifyIds
     * @return
     */
    List<String> selectClassifyNameByIds(Long[] classifyIds);
}
