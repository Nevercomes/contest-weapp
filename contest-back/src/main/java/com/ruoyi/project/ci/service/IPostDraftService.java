package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostDraft;

/**
 * 帖子草稿Service接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface IPostDraftService {
    /**
     * 查询帖子草稿
     *
     * @param id 帖子草稿ID
     * @return 帖子草稿
     */
    PostDraft selectPostDraftById(Long id);

    /**
     * 查询帖子草稿列表
     *
     * @param postDraft 帖子草稿
     * @return 帖子草稿集合
     */
    List<PostDraft> selectPostDraftList(PostDraft postDraft);

    /**
     * 查询帖子草稿选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostDraftOptions(PostDraft postDraft);

    /**
     * 新增帖子草稿
     *
     * @param postDraft 帖子草稿
     * @return 结果
     */
    int insertPostDraft(PostDraft postDraft);

    /**
     * 修改帖子草稿
     *
     * @param postDraft 帖子草稿
     * @return 结果
     */
    int updatePostDraft(PostDraft postDraft);

    /**
     * 批量删除帖子草稿
     *
     * @param ids 需要删除的帖子草稿ID
     * @return 结果
     */
    int deletePostDraftByIds(Long[] ids);

    /**
     * 删除帖子草稿信息
     *
     * @param id 帖子草稿ID
     * @return 结果
     */
    int deletePostDraftById(Long id);
}
