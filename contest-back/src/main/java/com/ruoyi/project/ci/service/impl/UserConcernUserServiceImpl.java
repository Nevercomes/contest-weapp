package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.UserConcernUserMapper;
import com.ruoyi.project.ci.domain.UserConcernUser;
import com.ruoyi.project.ci.service.IUserConcernUserService;

/**
 * 关注用户Service业务层处理
 *
 * @author sun
 * @date 2020-04-25
 */
@Service
public class UserConcernUserServiceImpl implements IUserConcernUserService {
    @Autowired
    private UserConcernUserMapper userConcernUserMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询关注用户
     *
     * @param id 关注用户ID
     * @return 关注用户
     */
    @Override
    public UserConcernUser selectUserConcernUserById(Long id) {
        UserConcernUser concernUser = userConcernUserMapper.selectUserConcernUserById(id);
        setAssociated(concernUser);
        return concernUser;
    }

    /**
     * 查询关注用户列表
     *
     * @param userConcernUser 关注用户
     * @return 关注用户
     */
    @Override
    public List<UserConcernUser> selectUserConcernUserList(UserConcernUser userConcernUser) {
        List<UserConcernUser> list = userConcernUserMapper.selectUserConcernUserList(userConcernUser);
        for (UserConcernUser userConcernUser1 : list) {
            setAssociated(userConcernUser);
        }
        return list;
    }

    /**
     * 查询关注用户选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectUserConcernUserOptions(UserConcernUser userConcernUser) {
        return userConcernUserMapper.selectUserConcernUserOptions(userConcernUser);
    }

    /**
     * 新增关注用户
     *
     * @param userConcernUser 关注用户
     * @return 结果
     */
    @Override
    public int insertUserConcernUser(UserConcernUser userConcernUser) {
        userConcernUser.preInsert();
        return userConcernUserMapper.insertUserConcernUser(userConcernUser);
    }

    /**
     * 修改关注用户
     *
     * @param userConcernUser 关注用户
     * @return 结果
     */
    @Override
    public int updateUserConcernUser(UserConcernUser userConcernUser) {
        userConcernUser.preUpdate();
        return userConcernUserMapper.updateUserConcernUser(userConcernUser);
    }

    /**
     * 批量删除关注用户
     *
     * @param ids 需要删除的关注用户ID
     * @return 结果
     */
    @Override
    public int deleteUserConcernUserByIds(Long[] ids) {
        return userConcernUserMapper.deleteUserConcernUserByIds(ids);
    }

    /**
     * 删除关注用户信息
     *
     * @param id 关注用户ID
     * @return 结果
     */
    @Override
    public int deleteUserConcernUserById(Long id) {
        return userConcernUserMapper.deleteUserConcernUserById(id);
    }

    private void setAssociated(UserConcernUser concernUser) {
        if (concernUser == null) return;
        if (concernUser.getUserId() != null) {
            concernUser.setAssUser(userMapper.selectAssUser(concernUser.getUserId()));
        }
    }
}
