package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.UserConcernCpMapper;
import com.ruoyi.project.ci.domain.UserConcernCp;
import com.ruoyi.project.ci.service.IUserConcernCpService;

/**
 * 关注竞赛Service业务层处理
 *
 * @author sun
 * @date 2020-04-25
 */
@Service
public class UserConcernCpServiceImpl implements IUserConcernCpService {
    @Autowired
    private UserConcernCpMapper userConcernCpMapper;

    /**
     * 查询关注竞赛
     *
     * @param id 关注竞赛ID
     * @return 关注竞赛
     */
    @Override
    public UserConcernCp selectUserConcernCpById(Long id) {
        return userConcernCpMapper.selectUserConcernCpById(id);
    }

    /**
     * 查询关注竞赛列表
     *
     * @param userConcernCp 关注竞赛
     * @return 关注竞赛
     */
    @Override
    public List<UserConcernCp> selectUserConcernCpList(UserConcernCp userConcernCp) {
        return userConcernCpMapper.selectUserConcernCpList(userConcernCp);
    }

    /**
     * 查询关注竞赛选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectUserConcernCpOptions(UserConcernCp userConcernCp) {
        return userConcernCpMapper.selectUserConcernCpOptions(userConcernCp);
    }

    /**
     * 新增关注竞赛
     *
     * @param userConcernCp 关注竞赛
     * @return 结果
     */
    @Override
    public int insertUserConcernCp(UserConcernCp userConcernCp) {
        userConcernCp.preInsert();
        return userConcernCpMapper.insertUserConcernCp(userConcernCp);
    }

    /**
     * 修改关注竞赛
     *
     * @param userConcernCp 关注竞赛
     * @return 结果
     */
    @Override
    public int updateUserConcernCp(UserConcernCp userConcernCp) {
        userConcernCp.preUpdate();
        return userConcernCpMapper.updateUserConcernCp(userConcernCp);
    }

    /**
     * 批量删除关注竞赛
     *
     * @param ids 需要删除的关注竞赛ID
     * @return 结果
     */
    @Override
    public int deleteUserConcernCpByIds(Long[] ids) {
        return userConcernCpMapper.deleteUserConcernCpByIds(ids);
    }

    /**
     * 删除关注竞赛信息
     *
     * @param id 关注竞赛ID
     * @return 结果
     */
    @Override
    public int deleteUserConcernCpById(Long id) {
        return userConcernCpMapper.deleteUserConcernCpById(id);
    }
}
