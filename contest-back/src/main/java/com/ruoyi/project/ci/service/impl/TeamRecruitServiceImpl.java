package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamRecruitMapper;
import com.ruoyi.project.ci.domain.TeamRecruit;
import com.ruoyi.project.ci.service.ITeamRecruitService;

/**
 * 队员招募Service业务层处理
 *
 * @author sun
 * @date 2020-04-21
 */
@Service
public class TeamRecruitServiceImpl implements ITeamRecruitService {
    @Autowired
    private TeamRecruitMapper teamRecruitMapper;

    /**
     * 查询队员招募
     *
     * @param id 队员招募ID
     * @return 队员招募
     */
    @Override
    public TeamRecruit selectTeamRecruitById(Long id) {
        return teamRecruitMapper.selectTeamRecruitById(id);
    }

    /**
     * 查询队员招募列表
     *
     * @param teamRecruit 队员招募
     * @return 队员招募
     */
    @Override
    public List<TeamRecruit> selectTeamRecruitList(TeamRecruit teamRecruit) {
        return teamRecruitMapper.selectTeamRecruitList(teamRecruit);
    }

    /**
     * 查询队员招募选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamRecruitOptions(TeamRecruit teamRecruit) {
        return teamRecruitMapper.selectTeamRecruitOptions(teamRecruit);
    }

    /**
     * 新增队员招募
     *
     * @param teamRecruit 队员招募
     * @return 结果
     */
    @Override
    public int insertTeamRecruit(TeamRecruit teamRecruit) {
        teamRecruit.preInsert();
        return teamRecruitMapper.insertTeamRecruit(teamRecruit);
    }

    /**
     * 修改队员招募
     *
     * @param teamRecruit 队员招募
     * @return 结果
     */
    @Override
    public int updateTeamRecruit(TeamRecruit teamRecruit) {
        teamRecruit.preUpdate();
        return teamRecruitMapper.updateTeamRecruit(teamRecruit);
    }

    /**
     * 批量删除队员招募
     *
     * @param ids 需要删除的队员招募ID
     * @return 结果
     */
    @Override
    public int deleteTeamRecruitByIds(Long[] ids) {
        return teamRecruitMapper.deleteTeamRecruitByIds(ids);
    }

    /**
     * 删除队员招募信息
     *
     * @param id 队员招募ID
     * @return 结果
     */
    @Override
    public int deleteTeamRecruitById(Long id) {
        return teamRecruitMapper.deleteTeamRecruitById(id);
    }
}
