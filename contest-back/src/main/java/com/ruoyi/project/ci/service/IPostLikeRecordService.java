package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostLikeRecord;

/**
 * 帖子点赞记录Service接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface IPostLikeRecordService {
    /**
     * 查询帖子点赞记录
     *
     * @param id 帖子点赞记录ID
     * @return 帖子点赞记录
     */
    PostLikeRecord selectPostLikeRecordById(Long id);

    /**
     * 查询帖子点赞记录列表
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 帖子点赞记录集合
     */
    List<PostLikeRecord> selectPostLikeRecordList(PostLikeRecord postLikeRecord);

    /**
     * 查询帖子点赞记录选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostLikeRecordOptions(PostLikeRecord postLikeRecord);

    /**
     * 新增帖子点赞记录
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 结果
     */
    int insertPostLikeRecord(PostLikeRecord postLikeRecord);

    /**
     * 修改帖子点赞记录
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 结果
     */
    int updatePostLikeRecord(PostLikeRecord postLikeRecord);

    /**
     * 批量删除帖子点赞记录
     *
     * @param ids 需要删除的帖子点赞记录ID
     * @return 结果
     */
    int deletePostLikeRecordByIds(Long[] ids);

    /**
     * 删除帖子点赞记录信息
     *
     * @param id 帖子点赞记录ID
     * @return 结果
     */
    int deletePostLikeRecordById(Long id);
}
