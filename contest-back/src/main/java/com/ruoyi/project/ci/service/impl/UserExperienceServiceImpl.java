package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper  .UserExperienceMapper;
import com.ruoyi.project.ci.domain.UserExperience;
import com.ruoyi.project.ci.service.IUserExperienceService;

/**
 * 竞赛经历Service业务层处理
 *
 * @author sun
 * @date 2020-04-25
 */
@Service
public class UserExperienceServiceImpl implements IUserExperienceService {
    @Autowired
    private UserExperienceMapper userExperienceMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询竞赛经历
     *
     * @param id 竞赛经历ID
     * @return 竞赛经历
     */
    @Override
    public UserExperience selectUserExperienceById(Long id) {
        UserExperience experience = userExperienceMapper.selectUserExperienceById(id);
        setCreated(experience);
        return experience;
    }

    /**
     * 查询竞赛经历列表
     *
     * @param userExperience 竞赛经历
     * @return 竞赛经历
     */
    @Override
    public List<UserExperience> selectUserExperienceList(UserExperience userExperience) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId()) && StringUtils.isEmpty(userExperience.getCreateBy())) {
            userExperience.setCreateBy(SecurityUtils.getUsername());
        }
        List<UserExperience> list = userExperienceMapper.selectUserExperienceList(userExperience);
        for (UserExperience experience : list) {
            setCreated(experience);
        }
        return list;
    }

    /**
     * 查询竞赛经历选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectUserExperienceOptions(UserExperience userExperience) {
        return userExperienceMapper.selectUserExperienceOptions(userExperience);
    }

    /**
     * 新增竞赛经历
     *
     * @param userExperience 竞赛经历
     * @return 结果
     */
    @Override
    public int insertUserExperience(UserExperience userExperience) {
        userExperience.preInsert();
        return userExperienceMapper.insertUserExperience(userExperience);
    }

    /**
     * 修改竞赛经历
     *
     * @param userExperience 竞赛经历
     * @return 结果
     */
    @Override
    public int updateUserExperience(UserExperience userExperience) {
        userExperience.preUpdate();
        return userExperienceMapper.updateUserExperience(userExperience);
    }

    /**
     * 批量删除竞赛经历
     *
     * @param ids 需要删除的竞赛经历ID
     * @return 结果
     */
    @Override
    public int deleteUserExperienceByIds(Long[] ids) {
        return userExperienceMapper.deleteUserExperienceByIds(ids);
    }

    /**
     * 删除竞赛经历信息
     *
     * @param id 竞赛经历ID
     * @return 结果
     */
    @Override
    public int deleteUserExperienceById(Long id) {
        return userExperienceMapper.deleteUserExperienceById(id);
    }

    /**
     * 查询竞赛经历大于1的username
     * @return
     */
    @Override
    public List<String> selectUsernameTalentList() {
        return userExperienceMapper.selectUsernameTalentList();
    }

    private void setCreated(UserExperience userExperience) {
        if (userExperience == null) return;
        if (StringUtils.isNotEmpty(userExperience.getCreateBy())) {
            userExperience.setCreateUser(userMapper.selectCreateUser(userExperience.getCreateBy()));
        }
    }
}
