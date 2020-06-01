package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.RecommendPost;

/**
 * 帖子推荐Service接口
 *
 * @author sun
 * @date 2020-05-31
 */
public interface IRecommendPostService {
    /**
     * 查询帖子推荐
     *
     * @param id 帖子推荐ID
     * @return 帖子推荐
     */
    RecommendPost selectRecommendPostById(Long id);

    /**
     * 查询帖子推荐列表
     *
     * @param recommendPost 帖子推荐
     * @return 帖子推荐集合
     */
    List<RecommendPost> selectRecommendPostList(RecommendPost recommendPost);

    /**
     * 查询帖子推荐选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectRecommendPostOptions(RecommendPost recommendPost);

    /**
     * 新增帖子推荐
     *
     * @param recommendPost 帖子推荐
     * @return 结果
     */
    int insertRecommendPost(RecommendPost recommendPost);

    /**
     * 修改帖子推荐
     *
     * @param recommendPost 帖子推荐
     * @return 结果
     */
    int updateRecommendPost(RecommendPost recommendPost);

    /**
     * 批量删除帖子推荐
     *
     * @param ids 需要删除的帖子推荐ID
     * @return 结果
     */
    int deleteRecommendPostByIds(Long[] ids);

    /**
     * 删除帖子推荐信息
     *
     * @param id 帖子推荐ID
     * @return 结果
     */
    int deleteRecommendPostById(Long id);
}
