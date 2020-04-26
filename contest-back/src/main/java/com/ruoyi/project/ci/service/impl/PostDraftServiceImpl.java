package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostDraftMapper;
import com.ruoyi.project.ci.domain.PostDraft;
import com.ruoyi.project.ci.service.IPostDraftService;

/**
 * 帖子草稿Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PostDraftServiceImpl implements IPostDraftService {
    @Autowired
    private PostDraftMapper postDraftMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询帖子草稿
     *
     * @param id 帖子草稿ID
     * @return 帖子草稿
     */
    @Override
    public PostDraft selectPostDraftById(Long id) {
        PostDraft postDraft = postDraftMapper.selectPostDraftById(id);
        setCreated(postDraft);
        return postDraft;
    }

    /**
     * 查询帖子草稿列表
     *
     * @param postDraft 帖子草稿
     * @return 帖子草稿
     */
    @Override
    public List<PostDraft> selectPostDraftList(PostDraft postDraft) {
        List<PostDraft> list = postDraftMapper.selectPostDraftList(postDraft);
        for (PostDraft postDraft1 : list) {
            setCreated(postDraft1);
        }
        return list;
    }

    /**
     * 查询帖子草稿选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostDraftOptions(PostDraft postDraft) {
        return postDraftMapper.selectPostDraftOptions(postDraft);
    }

    /**
     * 新增帖子草稿
     *
     * @param postDraft 帖子草稿
     * @return 结果
     */
    @Override
    public int insertPostDraft(PostDraft postDraft) {
        postDraft.preInsert();
        return postDraftMapper.insertPostDraft(postDraft);
    }

    /**
     * 修改帖子草稿
     *
     * @param postDraft 帖子草稿
     * @return 结果
     */
    @Override
    public int updatePostDraft(PostDraft postDraft) {
        postDraft.preUpdate();
        return postDraftMapper.updatePostDraft(postDraft);
    }

    /**
     * 批量删除帖子草稿
     *
     * @param ids 需要删除的帖子草稿ID
     * @return 结果
     */
    @Override
    public int deletePostDraftByIds(Long[] ids) {
        return postDraftMapper.deletePostDraftByIds(ids);
    }

    /**
     * 删除帖子草稿信息
     *
     * @param id 帖子草稿ID
     * @return 结果
     */
    @Override
    public int deletePostDraftById(Long id) {
        return postDraftMapper.deletePostDraftById(id);
    }

    private void setCreated(PostDraft postDraft) {
        if(postDraft == null) return;
        if (StringUtils.isNotEmpty(postDraft.getCreateBy())) {
            postDraft.setCreateUser(userMapper.selectCreateUser(postDraft.getCreateBy()));
        }
    }
}
