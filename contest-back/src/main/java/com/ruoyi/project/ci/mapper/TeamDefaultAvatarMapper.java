package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamDefaultAvatar;

/**
 * 默认团队头像Mapper接口
 *
 * @author sun
 * @date 2020-08-12
 */
public interface TeamDefaultAvatarMapper {
    /**
     * 查询默认团队头像
     *
     * @param id 默认团队头像ID
     * @return 默认团队头像
     */
    TeamDefaultAvatar selectTeamDefaultAvatarById(Long id);

    /**
     * 查询默认团队头像列表
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 默认团队头像集合
     */
    List<TeamDefaultAvatar> selectTeamDefaultAvatarList(TeamDefaultAvatar teamDefaultAvatar);

    /**
     * 查询默认团队头像选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamDefaultAvatarOptions(TeamDefaultAvatar teamDefaultAvatar);

    /**
     * 新增默认团队头像
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 结果
     */
    int insertTeamDefaultAvatar(TeamDefaultAvatar teamDefaultAvatar);

    /**
     * 修改默认团队头像
     *
     * @param teamDefaultAvatar 默认团队头像
     * @return 结果
     */
    int updateTeamDefaultAvatar(TeamDefaultAvatar teamDefaultAvatar);

    /**
     * 删除默认团队头像
     *
     * @param id 默认团队头像ID
     * @return 结果
     */
    int deleteTeamDefaultAvatarById(Long id);

    /**
     * 批量删除默认团队头像
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamDefaultAvatarByIds(Long[] ids);
}
