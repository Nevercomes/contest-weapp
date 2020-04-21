package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamInfo;

/**
 * 队伍信息Mapper接口
 *
 * @author sun
 * @date 2020-04-21
 */
public interface TeamInfoMapper {
    /**
     * 查询队伍信息
     *
     * @param id 队伍信息ID
     * @return 队伍信息
     */
    TeamInfo selectTeamInfoById(Long id);

    /**
     * 查询队伍信息列表
     *
     * @param teamInfo 队伍信息
     * @return 队伍信息集合
     */
    List<TeamInfo> selectTeamInfoList(TeamInfo teamInfo);

    /**
     * 查询队伍信息选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamInfoOptions(TeamInfo teamInfo);

    /**
     * 新增队伍信息
     *
     * @param teamInfo 队伍信息
     * @return 结果
     */
    int insertTeamInfo(TeamInfo teamInfo);

    /**
     * 修改队伍信息
     *
     * @param teamInfo 队伍信息
     * @return 结果
     */
    int updateTeamInfo(TeamInfo teamInfo);

    /**
     * 删除队伍信息
     *
     * @param id 队伍信息ID
     * @return 结果
     */
    int deleteTeamInfoById(Long id);

    /**
     * 批量删除队伍信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamInfoByIds(Long[] ids);
}
