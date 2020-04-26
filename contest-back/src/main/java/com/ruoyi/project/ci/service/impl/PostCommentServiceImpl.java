package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostCommentMapper;
import com.ruoyi.project.ci.domain.PostComment;
import com.ruoyi.project.ci.service.IPostCommentService;

/**
 * 帖子评论Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PostCommentServiceImpl implements IPostCommentService {
    @Autowired
    private PostCommentMapper postCommentMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询帖子评论
     *
     * @param id 帖子评论ID
     * @return 帖子评论
     */
    @Override
    public PostComment selectPostCommentById(Long id) {
        PostComment comment = postCommentMapper.selectPostCommentById(id);
        setCreated(comment);
        return comment;
    }

    /**
     * 查询帖子评论列表
     *
     * @param postComment 帖子评论
     * @return 帖子评论
     */
    @Override
    public List<PostComment> selectPostCommentList(PostComment postComment) {
        List<PostComment> list = postCommentMapper.selectPostCommentList(postComment);
        for (PostComment comment : list) {
            setCreated(comment);
        }
        return list;
    }

    /**
     * 查询帖子评论选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostCommentOptions(PostComment postComment) {
        return postCommentMapper.selectPostCommentOptions(postComment);
    }

    /**
     * 新增帖子评论
     *
     * @param postComment 帖子评论
     * @return 结果
     */
    @Override
    public int insertPostComment(PostComment postComment) {
        postComment.preInsert();
        return postCommentMapper.insertPostComment(postComment);
    }

    /**
     * 修改帖子评论
     *
     * @param postComment 帖子评论
     * @return 结果
     */
    @Override
    public int updatePostComment(PostComment postComment) {
        postComment.preUpdate();
        return postCommentMapper.updatePostComment(postComment);
    }

    /**
     * 批量删除帖子评论
     *
     * @param ids 需要删除的帖子评论ID
     * @return 结果
     */
    @Override
    public int deletePostCommentByIds(Long[] ids) {
        return postCommentMapper.deletePostCommentByIds(ids);
    }

    /**
     * 删除帖子评论信息
     *
     * @param id 帖子评论ID
     * @return 结果
     */
    @Override
    public int deletePostCommentById(Long id) {
        return postCommentMapper.deletePostCommentById(id);
    }

    /**
     * 注入创建者
     * @param postComment 注入对象
     */
    private void setCreated(PostComment postComment) {
        if(postComment == null) return;
        if (StringUtils.isNotEmpty(postComment.getCreateBy())) {
            postComment.setCreateUser(userMapper.selectCreateUser(postComment.getCreateBy()));
        }
    }
}
