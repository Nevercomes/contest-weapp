package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.ci.domain.TeamExpect;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamApplyMapper;
import com.ruoyi.project.ci.domain.TeamApply;
import com.ruoyi.project.ci.service.ITeamApplyService;

/**
 * 入队申请Service业务层处理
 *
 * @author sun
 * @date 2020-04-24
 */
@Service
public class TeamApplyServiceImpl implements ITeamApplyService {
    @Autowired
    private TeamApplyMapper teamApplyMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询入队申请
     *
     * @param id 入队申请ID
     * @return 入队申请
     */
    @Override
    public TeamApply selectTeamApplyById(Long id) {
        TeamApply apply = teamApplyMapper.selectTeamApplyById(id);
        setCreateUserBy(apply);
        return apply;
    }

    /**
     * 查询入队申请列表
     *
     * @param teamApply 入队申请
     * @return 入队申请
     */
    @Override
    public List<TeamApply> selectTeamApplyList(TeamApply teamApply) {
        List<TeamApply> list = teamApplyMapper.selectTeamApplyList(teamApply);
        for (TeamApply apply : list) {
            setCreateUserBy(apply);
        }
        return list;
    }

    /**
     * 查询入队申请选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamApplyOptions(TeamApply teamApply) {
        return teamApplyMapper.selectTeamApplyOptions(teamApply);
    }

    /**
     * 新增入队申请
     *
     * @param teamApply 入队申请
     * @return 结果
     */
    @Override
    public int insertTeamApply(TeamApply teamApply) {
        teamApply.preInsert();
        return teamApplyMapper.insertTeamApply(teamApply);
    }

    /**
     * 修改入队申请
     *
     * @param teamApply 入队申请
     * @return 结果
     */
    @Override
    public int updateTeamApply(TeamApply teamApply) {
        teamApply.preUpdate();
        return teamApplyMapper.updateTeamApply(teamApply);
    }

    /**
     * 批量删除入队申请
     *
     * @param ids 需要删除的入队申请ID
     * @return 结果
     */
    @Override
    public int deleteTeamApplyByIds(Long[] ids) {
        return teamApplyMapper.deleteTeamApplyByIds(ids);
    }

    /**
     * 删除入队申请信息
     *
     * @param id 入队申请ID
     * @return 结果
     */
    @Override
    public int deleteTeamApplyById(Long id) {
        return teamApplyMapper.deleteTeamApplyById(id);
    }

    /**
     * 查询所有收到的申请
     * @param teamApply
     * @return
     */
    @Override
    public List<TeamApply> selectTeamApplyReceive(TeamApply teamApply) {
        return teamApplyMapper.selectTeamApplyReceive(teamApply);
    }

    /**
     * 注入创建的用户
     * @param teamApply
     */
    private void setCreateUserBy(TeamApply teamApply) {
        if (teamApply == null) return;
        if (!StringUtils.isEmpty(teamApply.getCreateBy())) {
            teamApply.setCreateUser(userMapper.selectCreateUser(teamApply.getCreateBy()));
        }
    }


}
