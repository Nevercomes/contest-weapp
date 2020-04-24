package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamInvite;

/**
 * 入队邀请Mapper接口
 *
 * @author sun
 * @date 2020-04-24
 */
public interface TeamInviteMapper {
    /**
     * 查询入队邀请
     *
     * @param id 入队邀请ID
     * @return 入队邀请
     */
    TeamInvite selectTeamInviteById(Long id);

    /**
     * 查询入队邀请列表
     *
     * @param teamInvite 入队邀请
     * @return 入队邀请集合
     */
    List<TeamInvite> selectTeamInviteList(TeamInvite teamInvite);

    /**
     * 查询入队邀请选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamInviteOptions(TeamInvite teamInvite);

    /**
     * 新增入队邀请
     *
     * @param teamInvite 入队邀请
     * @return 结果
     */
    int insertTeamInvite(TeamInvite teamInvite);

    /**
     * 修改入队邀请
     *
     * @param teamInvite 入队邀请
     * @return 结果
     */
    int updateTeamInvite(TeamInvite teamInvite);

    /**
     * 删除入队邀请
     *
     * @param id 入队邀请ID
     * @return 结果
     */
    int deleteTeamInviteById(Long id);

    /**
     * 批量删除入队邀请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamInviteByIds(Long[] ids);
}
