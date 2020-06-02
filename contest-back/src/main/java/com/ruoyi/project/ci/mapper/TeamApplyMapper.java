package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.TeamApply;

/**
 * 入队申请Mapper接口
 *
 * @author sun
 * @date 2020-04-24
 */
public interface TeamApplyMapper {
    /**
     * 查询入队申请
     *
     * @param id 入队申请ID
     * @return 入队申请
     */
    TeamApply selectTeamApplyById(Long id);

    /**
     * 查询入队申请列表
     *
     * @param teamApply 入队申请
     * @return 入队申请集合
     */
    List<TeamApply> selectTeamApplyList(TeamApply teamApply);

    /**
     * 查询入队申请选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectTeamApplyOptions(TeamApply teamApply);

    /**
     * 新增入队申请
     *
     * @param teamApply 入队申请
     * @return 结果
     */
    int insertTeamApply(TeamApply teamApply);

    /**
     * 修改入队申请
     *
     * @param teamApply 入队申请
     * @return 结果
     */
    int updateTeamApply(TeamApply teamApply);

    /**
     * 删除入队申请
     *
     * @param id 入队申请ID
     * @return 结果
     */
    int deleteTeamApplyById(Long id);

    /**
     * 批量删除入队申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteTeamApplyByIds(Long[] ids);

    /**
     * 查询收到的申请
     * @param teamApply
     * @return
     */
    List<TeamApply> selectTeamApplyReceive(TeamApply teamApply);
}
