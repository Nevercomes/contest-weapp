package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostViewRecord;

/**
 * 帖子浏览记录Mapper接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface PostViewRecordMapper {
    /**
     * 查询帖子浏览记录
     *
     * @param id 帖子浏览记录ID
     * @return 帖子浏览记录
     */
    PostViewRecord selectPostViewRecordById(Long id);

    /**
     * 查询帖子浏览记录列表
     *
     * @param postViewRecord 帖子浏览记录
     * @return 帖子浏览记录集合
     */
    List<PostViewRecord> selectPostViewRecordList(PostViewRecord postViewRecord);

    /**
     * 查询帖子浏览记录选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostViewRecordOptions(PostViewRecord postViewRecord);

    /**
     * 新增帖子浏览记录
     *
     * @param postViewRecord 帖子浏览记录
     * @return 结果
     */
    int insertPostViewRecord(PostViewRecord postViewRecord);

    /**
     * 修改帖子浏览记录
     *
     * @param postViewRecord 帖子浏览记录
     * @return 结果
     */
    int updatePostViewRecord(PostViewRecord postViewRecord);

    /**
     * 删除帖子浏览记录
     *
     * @param id 帖子浏览记录ID
     * @return 结果
     */
    int deletePostViewRecordById(Long id);

    /**
     * 批量删除帖子浏览记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePostViewRecordByIds(Long[] ids);
}
