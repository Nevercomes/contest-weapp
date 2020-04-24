package com.ruoyi.project.ci.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.TeamExpectMapper;
import com.ruoyi.project.ci.domain.TeamExpect;
import com.ruoyi.project.ci.service.ITeamExpectService;

/**
 * 组队意愿Service业务层处理
 *
 * @author sun
 * @date 2020-04-21
 */
@Service
public class TeamExpectServiceImpl implements ITeamExpectService {
    @Autowired
    private TeamExpectMapper teamExpectMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询组队意愿
     *
     * @param id 组队意愿ID
     * @return 组队意愿
     */
    @Override
    public TeamExpect selectTeamExpectById(Long id) {
        TeamExpect teamExpect = teamExpectMapper.selectTeamExpectById(id);
        setCreateUserBy(teamExpect);
        return teamExpect;
    }

    /**
     * 查询组队意愿列表
     *
     * @param teamExpect 组队意愿
     * @return 组队意愿
     */
    @Override
    public List<TeamExpect> selectTeamExpectList(TeamExpect teamExpect) {
        List<TeamExpect> list = teamExpectMapper.selectTeamExpectList(teamExpect);
        for (TeamExpect t : list) {
            setCreateUserBy(t);
        }
        return list;
    }

    /**
     * 查询组队意愿选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectTeamExpectOptions(TeamExpect teamExpect) {
        return teamExpectMapper.selectTeamExpectOptions(teamExpect);
    }

    /**
     * 新增组队意愿
     *
     * @param teamExpect 组队意愿
     * @return 结果
     */
    @Override
    public int insertTeamExpect(TeamExpect teamExpect) {
        teamExpect.preInsert();
        return teamExpectMapper.insertTeamExpect(teamExpect);
    }

    /**
     * 修改组队意愿
     *
     * @param teamExpect 组队意愿
     * @return 结果
     */
    @Override
    public int updateTeamExpect(TeamExpect teamExpect) {
        teamExpect.preUpdate();
        return teamExpectMapper.updateTeamExpect(teamExpect);
    }

    /**
     * 批量删除组队意愿
     *
     * @param ids 需要删除的组队意愿ID
     * @return 结果
     */
    @Override
    public int deleteTeamExpectByIds(Long[] ids) {
        return teamExpectMapper.deleteTeamExpectByIds(ids);
    }

    /**
     * 删除组队意愿信息
     *
     * @param id 组队意愿ID
     * @return 结果
     */
    @Override
    public int deleteTeamExpectById(Long id) {
        return teamExpectMapper.deleteTeamExpectById(id);
    }

    /**
     * 注入创建的用户
     * @param teamExpect
     */
    private void setCreateUserBy(TeamExpect teamExpect) {
        if (teamExpect == null) return;
        if (!StringUtils.isEmpty(teamExpect.getCreateBy())) {
            teamExpect.setCreateUser(userMapper.selectCreateUser(teamExpect.getCreateBy()));
        }
    }
}
