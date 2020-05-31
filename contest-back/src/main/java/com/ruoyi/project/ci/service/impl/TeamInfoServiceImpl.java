package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.constant.DictConstant;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.ci.domain.TeamMember;
import com.ruoyi.project.ci.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamInfoMapper;
import com.ruoyi.project.ci.domain.TeamInfo;
import com.ruoyi.project.ci.service.ITeamInfoService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 队伍信息Service业务层处理
 *
 * @author sun
 * @date 2020-04-21
 */
@Service
public class TeamInfoServiceImpl implements ITeamInfoService {
    @Autowired
    private TeamInfoMapper teamInfoMapper;
    @Autowired
    private ITeamMemberService teamMemberService;

    /**
     * 查询队伍信息
     *
     * @param id 队伍信息ID
     * @return 队伍信息
     */
    @Override
    public TeamInfo selectTeamInfoById(Long id) {
        return teamInfoMapper.selectTeamInfoById(id);
    }

    /**
     * 查询队伍信息列表
     *
     * @param teamInfo 队伍信息
     * @return 队伍信息
     */
    @Override
    public List<TeamInfo> selectTeamInfoList(TeamInfo teamInfo) {
        return teamInfoMapper.selectTeamInfoList(teamInfo);
    }

    /**
     * 查询队伍信息选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamInfoOptions(TeamInfo teamInfo) {
        return teamInfoMapper.selectTeamInfoOptions(teamInfo);
    }

    /**
     * 新增队伍信息
     *
     * @param teamInfo 队伍信息
     * @return 结果
     */
    @Override
    @Transactional
    public TeamInfo insertTeamInfo(TeamInfo teamInfo) {
        teamInfo.preInsert();
        // 1. 插入队伍信息
        int res =  teamInfoMapper.insertTeamInfo(teamInfo);
        // 2. 插入队长信息
        TeamMember captain = new TeamMember();
        captain.setTeamId(teamInfo.getId());
        captain.setUserId(SecurityUtils.getUserId());
        captain.setIdentity(DictConstant.TEAM_MEMBER_IDENTITY_CAPTAIN);
        teamMemberService.insertTeamMember(captain);
        return teamInfo;
    }

    /**
     * 修改队伍信息
     *
     * @param teamInfo 队伍信息
     * @return 结果
     */
    @Override
    public int updateTeamInfo(TeamInfo teamInfo) {
        teamInfo.preUpdate();
        return teamInfoMapper.updateTeamInfo(teamInfo);
    }

    /**
     * 批量删除队伍信息
     *
     * @param ids 需要删除的队伍信息ID
     * @return 结果
     */
    @Override
    public int deleteTeamInfoByIds(Long[] ids) {
        return teamInfoMapper.deleteTeamInfoByIds(ids);
    }

    /**
     * 删除队伍信息信息
     *
     * @param id 队伍信息ID
     * @return 结果
     */
    @Override
    public int deleteTeamInfoById(Long id) {
        return teamInfoMapper.deleteTeamInfoById(id);
    }

    /**
     * 查询加入的队伍
     * @param teamInfo
     * @param userId
     * @return
     */
    @Override
    public List<TeamInfo> selectTeamInfoJoinList(TeamInfo teamInfo, Long userId) {
        return teamInfoMapper.selectTeamInfoJoinList(teamInfo, userId);
    }
}
