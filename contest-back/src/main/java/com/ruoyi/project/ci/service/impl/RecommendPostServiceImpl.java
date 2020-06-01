package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.RecommendPostMapper;
import com.ruoyi.project.ci.domain.RecommendPost;
import com.ruoyi.project.ci.service.IRecommendPostService;

/**
 * 帖子推荐Service业务层处理
 *
 * @author sun
 * @date 2020-05-31
 */
@Service
public class RecommendPostServiceImpl implements IRecommendPostService {
    @Autowired
    private RecommendPostMapper recommendPostMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询帖子推荐
     *
     * @param id 帖子推荐ID
     * @return 帖子推荐
     */
    @Override
    public RecommendPost selectRecommendPostById(Long id) {
        return recommendPostMapper.selectRecommendPostById(id);
    }

    /**
     * 查询帖子推荐列表
     *
     * @param recommendPost 帖子推荐
     * @return 帖子推荐
     */
    @Override
    public List<RecommendPost> selectRecommendPostList(RecommendPost recommendPost) {
        return recommendPostMapper.selectRecommendPostList(recommendPost);
    }

    /**
     * 查询帖子推荐选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectRecommendPostOptions(RecommendPost recommendPost) {
        return recommendPostMapper.selectRecommendPostOptions(recommendPost);
    }

    /**
     * 新增帖子推荐
     *
     * @param recommendPost 帖子推荐
     * @return 结果
     */
    @Override
    public int insertRecommendPost(RecommendPost recommendPost) {
        recommendPost.preInsert();
        return recommendPostMapper.insertRecommendPost(recommendPost);
    }

    /**
     * 修改帖子推荐
     *
     * @param recommendPost 帖子推荐
     * @return 结果
     */
    @Override
    public int updateRecommendPost(RecommendPost recommendPost) {
        recommendPost.preUpdate();
        return recommendPostMapper.updateRecommendPost(recommendPost);
    }

    /**
     * 批量删除帖子推荐
     *
     * @param ids 需要删除的帖子推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendPostByIds(Long[] ids) {
        return recommendPostMapper.deleteRecommendPostByIds(ids);
    }

    /**
     * 删除帖子推荐信息
     *
     * @param id 帖子推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendPostById(Long id) {
        return recommendPostMapper.deleteRecommendPostById(id);
    }

    /**
     * 注入创建者
     * @param recommendPost 注入对象
     */
    private void setCreated(RecommendPost recommendPost) {
        if(recommendPost == null) return;
        if (StringUtils.isNotEmpty(recommendPost.getCreateBy())) {
           // recommendPost.setCreateUser(userMapper.selectCreateUser(recommendPost.getCreateBy()));
        }
    }
}
