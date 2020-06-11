package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.ci.domain.PostContent;
import com.ruoyi.project.ci.mapper.PostContentMapper;
import com.ruoyi.project.system.mapper.SysUserMapper;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostInfoMapper;
import com.ruoyi.project.ci.domain.PostInfo;
import com.ruoyi.project.ci.service.IPostInfoService;

/**
 * 帖子信息Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PostInfoServiceImpl implements IPostInfoService {
    @Autowired
    private PostInfoMapper postInfoMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private PostContentMapper postContentMapper;

    /**
     * 查询帖子信息
     *
     * @param id 帖子信息ID
     * @return 帖子信息
     */
    @Override
    public PostInfo selectPostInfoById(Long id) {
        PostInfo info = postInfoMapper.selectPostInfoById(id);
        setCreated(info);
        return info;
    }

    /**
     * 查询帖子信息列表
     *
     * @param postInfo 帖子信息
     * @return 帖子信息
     */
    @Override
    public List<PostInfo> selectPostInfoList(PostInfo postInfo) {
        List<PostInfo> list = postInfoMapper.selectPostInfoList(postInfo);
        for (PostInfo info : list) {
            setCreated(info);
        }
        return list;
    }

    /**
     * 查询帖子信息选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostInfoOptions(PostInfo postInfo) {
        return postInfoMapper.selectPostInfoOptions(postInfo);
    }

    /**
     * 新增帖子信息
     *
     * @param postInfo 帖子信息
     * @return 结果
     */
    @Override
    public PostInfo insertPostInfo(PostInfo postInfo) {
        postInfo.preInsert();
        // 截取摘要
        if (StringUtils.isNotEmpty(postInfo.getContent()) && postInfo.getContent().length() >= 100) {
            postInfo.setSummary(postInfo.getContent().substring(0, 100));
        } else {
            postInfo.setSummary(postInfo.getContent());
        }
        int res =  postInfoMapper.insertPostInfo(postInfo);
        // 同时插入内容
        PostContent postContent = new PostContent();
        postContent.setPostId(postInfo.getId());
        postContent.setContent(postInfo.getContent());
        postContentMapper.insertPostContent(postContent);
        return postInfo;
    }

    /**
     * 修改帖子信息
     *
     * @param postInfo 帖子信息
     * @return 结果
     */
    @Override
    public PostInfo updatePostInfo(PostInfo postInfo) {
        postInfo.preUpdate();
        // 截取摘要
        if (StringUtils.isNotEmpty(postInfo.getContent()) && postInfo.getContent().length() >= 100) {
            postInfo.setSummary(postInfo.getContent().substring(0, 100));
        } else {
            postInfo.setSummary(postInfo.getContent());
        }
        int res =  postInfoMapper.updatePostInfo(postInfo);
        PostContent postContent = new PostContent();
        postContent.setPostId(postInfo.getId());
        postContent.setContent(postInfo.getContent());
        postContentMapper.updatePostContent(postContent);
        return postInfo;
    }

    /**
     * 批量删除帖子信息
     *
     * @param ids 需要删除的帖子信息ID
     * @return 结果
     */
    @Override
    public int deletePostInfoByIds(Long[] ids) {
        return postInfoMapper.deletePostInfoByIds(ids);
    }

    /**
     * 删除帖子信息信息
     *
     * @param id 帖子信息ID
     * @return 结果
     */
    @Override
    public int deletePostInfoById(Long id) {
        return postInfoMapper.deletePostInfoById(id);
    }

    /**
     * 发布帖子
     * @param postId
     * @return
     */
    @Override
    public int publicPost(Long postId) {
        return postInfoMapper.publicPost(postId);
    }

    @Override
    public List<PostInfo> selectPostInfoCollection(PostInfo postInfo) {
        List<PostInfo> list = postInfoMapper.selectPostInfoCollection(postInfo);
        for (PostInfo info : list) {
            setCreated(info);
        }
        return list;
    }

    private void setCreated(PostInfo postInfo) {
        if (postInfo == null) return;
        if (StringUtils.isNotEmpty(postInfo.getCreateBy())) {
            postInfo.setCreateUser(userMapper.selectCreateUser(postInfo.getCreateBy()));
        }
    }
}
