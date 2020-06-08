package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostContent;

/**
 * 帖子内容Mapper接口
 *
 * @author sun
 * @date 2020-06-08
 */
public interface PostContentMapper {
    /**
     * 查询帖子内容
     *
     * @param postId 帖子内容ID
     * @return 帖子内容
     */
    PostContent selectPostContentById(Long postId);

    /**
     * 查询帖子内容列表
     *
     * @param postContent 帖子内容
     * @return 帖子内容集合
     */
    List<PostContent> selectPostContentList(PostContent postContent);

    /**
     * 查询帖子内容选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostContentOptions(PostContent postContent);

    /**
     * 新增帖子内容
     *
     * @param postContent 帖子内容
     * @return 结果
     */
    int insertPostContent(PostContent postContent);

    /**
     * 修改帖子内容
     *
     * @param postContent 帖子内容
     * @return 结果
     */
    int updatePostContent(PostContent postContent);

    /**
     * 删除帖子内容
     *
     * @param postId 帖子内容ID
     * @return 结果
     */
    int deletePostContentById(Long postId);

    /**
     * 批量删除帖子内容
     *
     * @param postIds 需要删除的数据ID
     * @return 结果
     */
    int deletePostContentByIds(Long[] postIds);
}
