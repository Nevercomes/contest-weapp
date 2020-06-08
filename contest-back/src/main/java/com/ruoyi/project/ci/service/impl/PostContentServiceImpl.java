package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostContentMapper;
import com.ruoyi.project.ci.domain.PostContent;
import com.ruoyi.project.ci.service.IPostContentService;

/**
 * 帖子内容Service业务层处理
 *
 * @author sun
 * @date 2020-06-08
 */
@Service
public class PostContentServiceImpl implements IPostContentService {
    @Autowired
    private PostContentMapper postContentMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询帖子内容
     *
     * @param postId 帖子内容ID
     * @return 帖子内容
     */
    @Override
    public PostContent selectPostContentById(Long postId) {
        return postContentMapper.selectPostContentById(postId);
    }

    /**
     * 查询帖子内容列表
     *
     * @param postContent 帖子内容
     * @return 帖子内容
     */
    @Override
    public List<PostContent> selectPostContentList(PostContent postContent) {
        return postContentMapper.selectPostContentList(postContent);
    }

    /**
     * 查询帖子内容选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostContentOptions(PostContent postContent) {
        return postContentMapper.selectPostContentOptions(postContent);
    }

    /**
     * 新增帖子内容
     *
     * @param postContent 帖子内容
     * @return 结果
     */
    @Override
    public int insertPostContent(PostContent postContent) {
        postContent.preInsert();
        return postContentMapper.insertPostContent(postContent);
    }

    /**
     * 修改帖子内容
     *
     * @param postContent 帖子内容
     * @return 结果
     */
    @Override
    public int updatePostContent(PostContent postContent) {
        postContent.preUpdate();
        return postContentMapper.updatePostContent(postContent);
    }

    /**
     * 批量删除帖子内容
     *
     * @param postIds 需要删除的帖子内容ID
     * @return 结果
     */
    @Override
    public int deletePostContentByIds(Long[] postIds) {
        return postContentMapper.deletePostContentByIds(postIds);
    }

    /**
     * 删除帖子内容信息
     *
     * @param postId 帖子内容ID
     * @return 结果
     */
    @Override
    public int deletePostContentById(Long postId) {
        return postContentMapper.deletePostContentById(postId);
    }

    /**
     * 注入创建者
     * @param postContent 注入对象
     */
    private void setCreated(PostContent postContent) {
        if(postContent == null) return;
        if (StringUtils.isNotEmpty(postContent.getCreateBy())) {
           // postContent.setCreateUser(userMapper.selectCreateUser(postContent.getCreateBy()));
        }
    }
}
