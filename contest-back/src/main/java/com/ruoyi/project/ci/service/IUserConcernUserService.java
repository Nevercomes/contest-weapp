package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.UserConcernUser;

/**
 * 关注用户Service接口
 *
 * @author sun
 * @date 2020-04-25
 */
public interface IUserConcernUserService {
    /**
     * 查询关注用户
     *
     * @param id 关注用户ID
     * @return 关注用户
     */
    UserConcernUser selectUserConcernUserById(Long id);

    /**
     * 查询关注用户列表
     *
     * @param userConcernUser 关注用户
     * @return 关注用户集合
     */
    List<UserConcernUser> selectUserConcernUserList(UserConcernUser userConcernUser);

    /**
     * 查询关注用户选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectUserConcernUserOptions(UserConcernUser userConcernUser);

    /**
     * 新增关注用户
     *
     * @param userConcernUser 关注用户
     * @return 结果
     */
    UserConcernUser insertUserConcernUser(UserConcernUser userConcernUser);

    /**
     * 修改关注用户
     *
     * @param userConcernUser 关注用户
     * @return 结果
     */
    int updateUserConcernUser(UserConcernUser userConcernUser);

    /**
     * 批量删除关注用户
     *
     * @param ids 需要删除的关注用户ID
     * @return 结果
     */
    int deleteUserConcernUserByIds(Long[] ids);

    /**
     * 删除关注用户信息
     *
     * @param id 关注用户ID
     * @return 结果
     */
    int deleteUserConcernUserById(Long id);
}
