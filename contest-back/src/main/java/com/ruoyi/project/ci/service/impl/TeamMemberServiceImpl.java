package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamMemberMapper;
import com.ruoyi.project.ci.domain.TeamMember;
import com.ruoyi.project.ci.service.ITeamMemberService;

/**
 * 队伍成员Service业务层处理
 *
 * @author sun
 * @date 2020-04-21
 */
@Service
public class TeamMemberServiceImpl implements ITeamMemberService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询队伍成员
     *
     * @param id 队伍成员ID
     * @return 队伍成员
     */
    @Override
    public TeamMember selectTeamMemberById(Long id) {
        TeamMember member = teamMemberMapper.selectTeamMemberById(id);
        setAssociated(member);
        return member;
    }

    /**
     * 查询队伍成员列表
     *
     * @param teamMember 队伍成员
     * @return 队伍成员
     */
    @Override
    public List<TeamMember> selectTeamMemberList(TeamMember teamMember) {
        List<TeamMember> list = teamMemberMapper.selectTeamMemberList(teamMember);
        for (TeamMember member : list) {
            setAssociated(member);
        }
        return list;
    }

    /**
     * 查询队伍成员选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamMemberOptions(TeamMember teamMember) {
        return teamMemberMapper.selectTeamMemberOptions(teamMember);
    }

    /**
     * 新增队伍成员
     *
     * @param teamMember 队伍成员
     * @return 结果
     */
    @Override
    public int insertTeamMember(TeamMember teamMember) {
        teamMember.preInsert();
        return teamMemberMapper.insertTeamMember(teamMember);
    }

    /**
     * 修改队伍成员
     *
     * @param teamMember 队伍成员
     * @return 结果
     */
    @Override
    public int updateTeamMember(TeamMember teamMember) {
        teamMember.preUpdate();
        return teamMemberMapper.updateTeamMember(teamMember);
    }

    /**
     * 批量删除队伍成员
     *
     * @param ids 需要删除的队伍成员ID
     * @return 结果
     */
    @Override
    public int deleteTeamMemberByIds(Long[] ids) {
        return teamMemberMapper.deleteTeamMemberByIds(ids);
    }

    /**
     * 删除队伍成员信息
     *
     * @param id 队伍成员ID
     * @return 结果
     */
    @Override
    public int deleteTeamMemberById(Long id) {
        return teamMemberMapper.deleteTeamMemberById(id);
    }

    /**
     * 通过userId设置相关用户
     */
    private void setAssociated(TeamMember teamMember) {
        if (teamMember == null) return;
        if (teamMember.getUserId() != null) {
            teamMember.setAssUser(userMapper.selectAssUser(teamMember.getUserId()));
        }
    }
}
