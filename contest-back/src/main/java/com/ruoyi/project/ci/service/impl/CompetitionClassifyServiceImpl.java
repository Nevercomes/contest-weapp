package com.ruoyi.project.ci.service.impl;

import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.ci.domain.CompetitionClassify;
import com.ruoyi.project.ci.mapper.CompetitionClassifyMapper;
import com.ruoyi.project.ci.service.ICompetitionClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 竞赛分类Service业务层处理
 *
 * @author sun
 * @date 2020-04-17
 */
@Service
public class CompetitionClassifyServiceImpl implements ICompetitionClassifyService {
    @Autowired
    private CompetitionClassifyMapper competitionClassifyMapper;

    /**
     * 查询竞赛分类
     *
     * @param id 竞赛分类ID
     * @return 竞赛分类
     */
    @Override
    public CompetitionClassify selectCompetitionClassifyById(Long id) {
        return competitionClassifyMapper.selectCompetitionClassifyById(id);
    }

    /**
     * 查询竞赛分类列表
     *
     * @param competitionClassify 竞赛分类
     * @return 竞赛分类
     */
    @Override
    public List<CompetitionClassify> selectCompetitionClassifyList(CompetitionClassify competitionClassify) {
        return competitionClassifyMapper.selectCompetitionClassifyList(competitionClassify);
    }

    /**
     * 新增竞赛分类
     *
     * @param competitionClassify 竞赛分类
     * @return 结果
     */
    @Override
    public int insertCompetitionClassify(CompetitionClassify competitionClassify) {
        competitionClassify.preInsert();
        return competitionClassifyMapper.insertCompetitionClassify(competitionClassify);
    }

    /**
     * 修改竞赛分类
     *
     * @param competitionClassify 竞赛分类
     * @return 结果
     */
    @Override
    public int updateCompetitionClassify(CompetitionClassify competitionClassify) {
        competitionClassify.preUpdate();
        return competitionClassifyMapper.updateCompetitionClassify(competitionClassify);
    }

    /**
     * 批量删除竞赛分类
     *
     * @param ids 需要删除的竞赛分类ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionClassifyByIds(Long[] ids) {
        return competitionClassifyMapper.deleteCompetitionClassifyByIds(ids);
    }

    /**
     * 删除竞赛分类信息
     *
     * @param id 竞赛分类ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionClassifyById(Long id) {
        return competitionClassifyMapper.deleteCompetitionClassifyById(id);
    }

    /**
     * 构建前端所需的竞赛分类树
     * @param list
     * @return
     */
    @Override
    public List<TreeSelect> buildCompetitionClassifyTreeSelect(List<CompetitionClassify> list) {
        List<CompetitionClassify> list1 = buildCompetitionClassifyTree(list);
        return list1.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据竞赛Id查询分类树
     * @param infoId
     * @return
     */
    @Override
    public List<Integer> selectCompetitionClassifyListByInfoId(Long infoId) {
        return competitionClassifyMapper.selectCompetitionClassifyListByInfoId(infoId);
    }

    private List<CompetitionClassify> buildCompetitionClassifyTree(List<CompetitionClassify> list) {
        List<CompetitionClassify> returnList = new ArrayList<>();
        for (CompetitionClassify t : list) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = list;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<CompetitionClassify> list, CompetitionClassify t) {
        // 得到子节点列表
        List<CompetitionClassify> childList = getChildList(list, t);
        t.setChildrens(childList);
        for (CompetitionClassify tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                for (CompetitionClassify n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<CompetitionClassify> getChildList(List<CompetitionClassify> list, CompetitionClassify t) {
        List<CompetitionClassify> tlist = new ArrayList<>();
        for (CompetitionClassify n : list) {
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<CompetitionClassify> list, CompetitionClassify t) {
        return getChildList(list, t).size() > 0;
    }
}
