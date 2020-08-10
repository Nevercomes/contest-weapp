package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.UserExperience;

/**
 * 竞赛经历Mapper接口
 *
 * @author sun
 * @date 2020-04-25
 */
public interface UserExperienceMapper {
    /**
     * 查询竞赛经历
     *
     * @param id 竞赛经历ID
     * @return 竞赛经历
     */
    UserExperience selectUserExperienceById(Long id);

    /**
     * 查询竞赛经历列表
     *
     * @param userExperience 竞赛经历
     * @return 竞赛经历集合
     */
    List<UserExperience> selectUserExperienceList(UserExperience userExperience);

    /**
     * 查询竞赛经历选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectUserExperienceOptions(UserExperience userExperience);

    /**
     * 新增竞赛经历
     *
     * @param userExperience 竞赛经历
     * @return 结果
     */
    int insertUserExperience(UserExperience userExperience);

    /**
     * 修改竞赛经历
     *
     * @param userExperience 竞赛经历
     * @return 结果
     */
    int updateUserExperience(UserExperience userExperience);

    /**
     * 删除竞赛经历
     *
     * @param id 竞赛经历ID
     * @return 结果
     */
    int deleteUserExperienceById(Long id);

    /**
     * 批量删除竞赛经历
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteUserExperienceByIds(Long[] ids);

    /**
     * 查询竞赛经历大于1的username
     * @return
     */
    List<String> selectUsernameTalentList();

}
