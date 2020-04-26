package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostComment;

/**
 * 帖子评论Mapper接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface PostCommentMapper {
    /**
     * 查询帖子评论
     *
     * @param id 帖子评论ID
     * @return 帖子评论
     */
    PostComment selectPostCommentById(Long id);

    /**
     * 查询帖子评论列表
     *
     * @param postComment 帖子评论
     * @return 帖子评论集合
     */
    List<PostComment> selectPostCommentList(PostComment postComment);

    /**
     * 查询帖子评论选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostCommentOptions(PostComment postComment);

    /**
     * 新增帖子评论
     *
     * @param postComment 帖子评论
     * @return 结果
     */
    int insertPostComment(PostComment postComment);

    /**
     * 修改帖子评论
     *
     * @param postComment 帖子评论
     * @return 结果
     */
    int updatePostComment(PostComment postComment);

    /**
     * 删除帖子评论
     *
     * @param id 帖子评论ID
     * @return 结果
     */
    int deletePostCommentById(Long id);

    /**
     * 批量删除帖子评论
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePostCommentByIds(Long[] ids);
}
