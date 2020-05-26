package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.UserConcernCp;

/**
 * 关注竞赛Service接口
 *
 * @author sun
 * @date 2020-04-25
 */
public interface IUserConcernCpService {
    /**
     * 查询关注竞赛
     *
     * @param id 关注竞赛ID
     * @return 关注竞赛
     */
    UserConcernCp selectUserConcernCpById(Long id);

    /**
     * 查询关注竞赛列表
     *
     * @param userConcernCp 关注竞赛
     * @return 关注竞赛集合
     */
    List<UserConcernCp> selectUserConcernCpList(UserConcernCp userConcernCp);

    /**
     * 查询关注竞赛选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectUserConcernCpOptions(UserConcernCp userConcernCp);

    /**
     * 新增关注竞赛
     *
     * @param userConcernCp 关注竞赛
     * @return 结果
     */
    UserConcernCp insertUserConcernCp(UserConcernCp userConcernCp);

    /**
     * 修改关注竞赛
     *
     * @param userConcernCp 关注竞赛
     * @return 结果
     */
    int updateUserConcernCp(UserConcernCp userConcernCp);

    /**
     * 批量删除关注竞赛
     *
     * @param ids 需要删除的关注竞赛ID
     * @return 结果
     */
    int deleteUserConcernCpByIds(Long[] ids);

    /**
     * 删除关注竞赛信息
     *
     * @param id 关注竞赛ID
     * @return 结果
     */
    int deleteUserConcernCpById(Long id);
}
