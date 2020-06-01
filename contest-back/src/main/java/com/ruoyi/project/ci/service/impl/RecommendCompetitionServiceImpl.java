package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.RecommendCompetitionMapper;
import com.ruoyi.project.ci.domain.RecommendCompetition;
import com.ruoyi.project.ci.service.IRecommendCompetitionService;

/**
 * 竞赛推荐Service业务层处理
 *
 * @author sun
 * @date 2020-05-31
 */
@Service
public class RecommendCompetitionServiceImpl implements IRecommendCompetitionService {
    @Autowired
    private RecommendCompetitionMapper recommendCompetitionMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询竞赛推荐
     *
     * @param id 竞赛推荐ID
     * @return 竞赛推荐
     */
    @Override
    public RecommendCompetition selectRecommendCompetitionById(Long id) {
        return recommendCompetitionMapper.selectRecommendCompetitionById(id);
    }

    /**
     * 查询竞赛推荐列表
     *
     * @param recommendCompetition 竞赛推荐
     * @return 竞赛推荐
     */
    @Override
    public List<RecommendCompetition> selectRecommendCompetitionList(RecommendCompetition recommendCompetition) {
        return recommendCompetitionMapper.selectRecommendCompetitionList(recommendCompetition);
    }

    /**
     * 查询竞赛推荐选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectRecommendCompetitionOptions(RecommendCompetition recommendCompetition) {
        return recommendCompetitionMapper.selectRecommendCompetitionOptions(recommendCompetition);
    }

    /**
     * 新增竞赛推荐
     *
     * @param recommendCompetition 竞赛推荐
     * @return 结果
     */
    @Override
    public int insertRecommendCompetition(RecommendCompetition recommendCompetition) {
        recommendCompetition.preInsert();
        return recommendCompetitionMapper.insertRecommendCompetition(recommendCompetition);
    }

    /**
     * 修改竞赛推荐
     *
     * @param recommendCompetition 竞赛推荐
     * @return 结果
     */
    @Override
    public int updateRecommendCompetition(RecommendCompetition recommendCompetition) {
        recommendCompetition.preUpdate();
        return recommendCompetitionMapper.updateRecommendCompetition(recommendCompetition);
    }

    /**
     * 批量删除竞赛推荐
     *
     * @param ids 需要删除的竞赛推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendCompetitionByIds(Long[] ids) {
        return recommendCompetitionMapper.deleteRecommendCompetitionByIds(ids);
    }

    /**
     * 删除竞赛推荐信息
     *
     * @param id 竞赛推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendCompetitionById(Long id) {
        return recommendCompetitionMapper.deleteRecommendCompetitionById(id);
    }

    /**
     * 注入创建者
     * @param recommendCompetition 注入对象
     */
    private void setCreated(RecommendCompetition recommendCompetition) {
        if(recommendCompetition == null) return;
        if (StringUtils.isNotEmpty(recommendCompetition.getCreateBy())) {
           // recommendCompetition.setCreateUser(userMapper.selectCreateUser(recommendCompetition.getCreateBy()));
        }
    }
}
