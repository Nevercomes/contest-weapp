package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionTimelineMapper;
import com.ruoyi.project.ci.domain.CompetitionTimeline;
import com.ruoyi.project.ci.service.ICompetitionTimelineService;

/**
 * 竞赛资讯Service业务层处理
 *
 * @author sun
 * @date 2020-08-10
 */
@Service
public class CompetitionTimelineServiceImpl implements ICompetitionTimelineService {
    @Autowired
    private CompetitionTimelineMapper competitionTimelineMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询竞赛资讯
     *
     * @param id 竞赛资讯ID
     * @return 竞赛资讯
     */
    @Override
    public CompetitionTimeline selectCompetitionTimelineById(Long id) {
        return competitionTimelineMapper.selectCompetitionTimelineById(id);
    }

    /**
     * 查询竞赛资讯列表
     *
     * @param competitionTimeline 竞赛资讯
     * @return 竞赛资讯
     */
    @Override
    public List<CompetitionTimeline> selectCompetitionTimelineList(CompetitionTimeline competitionTimeline) {
        return competitionTimelineMapper.selectCompetitionTimelineList(competitionTimeline);
    }

    /**
     * 查询竞赛资讯选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectCompetitionTimelineOptions(CompetitionTimeline competitionTimeline) {
        return competitionTimelineMapper.selectCompetitionTimelineOptions(competitionTimeline);
    }

    /**
     * 新增竞赛资讯
     *
     * @param competitionTimeline 竞赛资讯
     * @return 结果
     */
    @Override
    public int insertCompetitionTimeline(CompetitionTimeline competitionTimeline) {
        competitionTimeline.preInsert();
        return competitionTimelineMapper.insertCompetitionTimeline(competitionTimeline);
    }

    /**
     * 修改竞赛资讯
     *
     * @param competitionTimeline 竞赛资讯
     * @return 结果
     */
    @Override
    public int updateCompetitionTimeline(CompetitionTimeline competitionTimeline) {
        competitionTimeline.preUpdate();
        return competitionTimelineMapper.updateCompetitionTimeline(competitionTimeline);
    }

    /**
     * 批量删除竞赛资讯
     *
     * @param ids 需要删除的竞赛资讯ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionTimelineByIds(Long[] ids) {
        return competitionTimelineMapper.deleteCompetitionTimelineByIds(ids);
    }

    /**
     * 删除竞赛资讯信息
     *
     * @param id 竞赛资讯ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionTimelineById(Long id) {
        return competitionTimelineMapper.deleteCompetitionTimelineById(id);
    }

    /**
     * 注入创建者
     * @param competitionTimeline 注入对象
     */
    private void setCreated(CompetitionTimeline competitionTimeline) {
        if(competitionTimeline == null) return;
        if (StringUtils.isNotEmpty(competitionTimeline.getCreateBy())) {
           // competitionTimeline.setCreateUser(userMapper.selectCreateUser(competitionTimeline.getCreateBy()));
        }
    }
}
