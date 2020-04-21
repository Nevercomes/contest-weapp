package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamRecruit;

/**
 * 队员招募Mapper接口
 *
 * @author sun
 * @date 2020-04-21
 */
public interface TeamRecruitMapper {
    /**
     * 查询队员招募
     *
     * @param id 队员招募ID
     * @return 队员招募
     */
    TeamRecruit selectTeamRecruitById(Long id);

    /**
     * 查询队员招募列表
     *
     * @param teamRecruit 队员招募
     * @return 队员招募集合
     */
    List<TeamRecruit> selectTeamRecruitList(TeamRecruit teamRecruit);

    /**
     * 查询队员招募选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamRecruitOptions(TeamRecruit teamRecruit);

    /**
     * 新增队员招募
     *
     * @param teamRecruit 队员招募
     * @return 结果
     */
    int insertTeamRecruit(TeamRecruit teamRecruit);

    /**
     * 修改队员招募
     *
     * @param teamRecruit 队员招募
     * @return 结果
     */
    int updateTeamRecruit(TeamRecruit teamRecruit);

    /**
     * 删除队员招募
     *
     * @param id 队员招募ID
     * @return 结果
     */
    int deleteTeamRecruitById(Long id);

    /**
     * 批量删除队员招募
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamRecruitByIds(Long[] ids);
}
