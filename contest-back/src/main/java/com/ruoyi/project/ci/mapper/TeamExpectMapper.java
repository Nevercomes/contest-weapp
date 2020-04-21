package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamExpect;

/**
 * 组队意愿Mapper接口
 *
 * @author sun
 * @date 2020-04-21
 */
public interface TeamExpectMapper {
    /**
     * 查询组队意愿
     *
     * @param id 组队意愿ID
     * @return 组队意愿
     */
    TeamExpect selectTeamExpectById(Long id);

    /**
     * 查询组队意愿列表
     *
     * @param teamExpect 组队意愿
     * @return 组队意愿集合
     */
    List<TeamExpect> selectTeamExpectList(TeamExpect teamExpect);

    /**
     * 查询组队意愿选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamExpectOptions(TeamExpect teamExpect);

    /**
     * 新增组队意愿
     *
     * @param teamExpect 组队意愿
     * @return 结果
     */
    int insertTeamExpect(TeamExpect teamExpect);

    /**
     * 修改组队意愿
     *
     * @param teamExpect 组队意愿
     * @return 结果
     */
    int updateTeamExpect(TeamExpect teamExpect);

    /**
     * 删除组队意愿
     *
     * @param id 组队意愿ID
     * @return 结果
     */
    int deleteTeamExpectById(Long id);

    /**
     * 批量删除组队意愿
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamExpectByIds(Long[] ids);
}
