package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.ci.domain.TeamMember;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamInviteMapper;
import com.ruoyi.project.ci.domain.TeamInvite;
import com.ruoyi.project.ci.service.ITeamInviteService;

/**
 * 入队邀请Service业务层处理
 *
 * @author sun
 * @date 2020-04-24
 */
@Service
public class TeamInviteServiceImpl implements ITeamInviteService {
    @Autowired
    private TeamInviteMapper teamInviteMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询入队邀请
     *
     * @param id 入队邀请ID
     * @return 入队邀请
     */
    @Override
    public TeamInvite selectTeamInviteById(Long id) {
        TeamInvite invite = teamInviteMapper.selectTeamInviteById(id);
        setAssociated(invite);
        return invite;
    }

    /**
     * 查询入队邀请列表
     *
     * @param teamInvite 入队邀请
     * @return 入队邀请
     */
    @Override
    public List<TeamInvite> selectTeamInviteList(TeamInvite teamInvite) {
        List<TeamInvite> list = teamInviteMapper.selectTeamInviteList(teamInvite);
        for (TeamInvite invite : list) {
            setAssociated(invite);
        }
        return list;
    }

    /**
     * 查询入队邀请选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamInviteOptions(TeamInvite teamInvite) {
        return teamInviteMapper.selectTeamInviteOptions(teamInvite);
    }

    /**
     * 新增入队邀请
     *
     * @param teamInvite 入队邀请
     * @return 结果
     */
    @Override
    public int insertTeamInvite(TeamInvite teamInvite) {
        teamInvite.preInsert();
        return teamInviteMapper.insertTeamInvite(teamInvite);
    }

    /**
     * 修改入队邀请
     *
     * @param teamInvite 入队邀请
     * @return 结果
     */
    @Override
    public int updateTeamInvite(TeamInvite teamInvite) {
        teamInvite.preUpdate();
        return teamInviteMapper.updateTeamInvite(teamInvite);
    }

    /**
     * 批量删除入队邀请
     *
     * @param ids 需要删除的入队邀请ID
     * @return 结果
     */
    @Override
    public int deleteTeamInviteByIds(Long[] ids) {
        return teamInviteMapper.deleteTeamInviteByIds(ids);
    }

    /**
     * 删除入队邀请信息
     *
     * @param id 入队邀请ID
     * @return 结果
     */
    @Override
    public int deleteTeamInviteById(Long id) {
        return teamInviteMapper.deleteTeamInviteById(id);
    }

    /**
     * 通过userId设置相关用户
     */
    private void setAssociated(TeamInvite teamInvite) {
        if (teamInvite == null) return;
        if (teamInvite.getInvitedUserId() != null) {
            teamInvite.setAssUser(userMapper.selectAssUser(teamInvite.getInvitedUserId()));
        }
    }
}
