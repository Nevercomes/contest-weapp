package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamMember;

/**
 * 队伍成员Mapper接口
 *
 * @author sun
 * @date 2020-04-21
 */
public interface TeamMemberMapper {
    /**
     * 查询队伍成员
     *
     * @param id 队伍成员ID
     * @return 队伍成员
     */
    TeamMember selectTeamMemberById(Long id);

    /**
     * 查询队伍成员列表
     *
     * @param teamMember 队伍成员
     * @return 队伍成员集合
     */
    List<TeamMember> selectTeamMemberList(TeamMember teamMember);

    /**
     * 查询队伍成员选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamMemberOptions(TeamMember teamMember);

    /**
     * 新增队伍成员
     *
     * @param teamMember 队伍成员
     * @return 结果
     */
    int insertTeamMember(TeamMember teamMember);

    /**
     * 修改队伍成员
     *
     * @param teamMember 队伍成员
     * @return 结果
     */
    int updateTeamMember(TeamMember teamMember);

    /**
     * 删除队伍成员
     *
     * @param id 队伍成员ID
     * @return 结果
     */
    int deleteTeamMemberById(Long id);

    /**
     * 批量删除队伍成员
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamMemberByIds(Long[] ids);
}
