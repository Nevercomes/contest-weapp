package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamDefaultAvatarMapper;
import com.ruoyi.project.ci.domain.TeamDefaultAvatar;
import com.ruoyi.project.ci.service.ITeamDefaultAvatarService;

/**
 * 默认团队头像Service业务层处理
 *
 * @author sun
 * @date 2020-08-12
 */
@Service
public class TeamDefaultAvatarServiceImpl implements ITeamDefaultAvatarService {
    @Autowired
    private TeamDefaultAvatarMapper teamDefaultAvatarMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询默认团队头像
     *
     * @param id 默认团队头像ID
     * @return 默认团队头像
     */
    @Override
    public TeamDefaultAvatar selectTeamDefaultAvatarById(Long id) {
        return teamDefaultAvatarMapper.selectTeamDefaultAvatarById(id);
    }

    /**
     * 查询默认团队头像列表
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 默认团队头像
     */
    @Override
    public List<TeamDefaultAvatar> selectTeamDefaultAvatarList(TeamDefaultAvatar teamDefaultAvatar) {
        return teamDefaultAvatarMapper.selectTeamDefaultAvatarList(teamDefaultAvatar);
    }

    /**
     * 查询默认团队头像选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamDefaultAvatarOptions(TeamDefaultAvatar teamDefaultAvatar) {
        return teamDefaultAvatarMapper.selectTeamDefaultAvatarOptions(teamDefaultAvatar);
    }

    /**
     * 新增默认团队头像
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 结果
     */
    @Override
    public int insertTeamDefaultAvatar(TeamDefaultAvatar teamDefaultAvatar) {
        teamDefaultAvatar.preInsert();
        return teamDefaultAvatarMapper.insertTeamDefaultAvatar(teamDefaultAvatar);
    }

    /**
     * 修改默认团队头像
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 结果
     */
    @Override
    public int updateTeamDefaultAvatar(TeamDefaultAvatar teamDefaultAvatar) {
        teamDefaultAvatar.preUpdate();
        return teamDefaultAvatarMapper.updateTeamDefaultAvatar(teamDefaultAvatar);
    }

    /**
     * 批量删除默认团队头像
     *
     * @param ids 需要删除的默认团队头像ID
     * @return 结果
     */
    @Override
    public int deleteTeamDefaultAvatarByIds(Long[] ids) {
        return teamDefaultAvatarMapper.deleteTeamDefaultAvatarByIds(ids);
    }

    /**
     * 删除默认团队头像信息
     *
     * @param id 默认团队头像ID
     * @return 结果
     */
    @Override
    public int deleteTeamDefaultAvatarById(Long id) {
        return teamDefaultAvatarMapper.deleteTeamDefaultAvatarById(id);
    }

    /**
     * 选取一张随机的队伍头像
     * @return
     */
    @Override
    public TeamDefaultAvatar getRandomTeamDefaultAvatar() {
        List<TeamDefaultAvatar> list = teamDefaultAvatarMapper.selectTeamDefaultAvatarList(new TeamDefaultAvatar());
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    /**
     * 注入创建者
     * @param teamDefaultAvatar 注入对象
     */
    private void setCreated(TeamDefaultAvatar teamDefaultAvatar) {
        if(teamDefaultAvatar == null) return;
        if (StringUtils.isNotEmpty(teamDefaultAvatar.getCreateBy())) {
           // teamDefaultAvatar.setCreateUser(userMapper.selectCreateUser(teamDefaultAvatar.getCreateBy()));
        }
    }
}
