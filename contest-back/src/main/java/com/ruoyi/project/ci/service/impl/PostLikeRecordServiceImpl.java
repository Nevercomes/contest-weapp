package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostLikeRecordMapper;
import com.ruoyi.project.ci.domain.PostLikeRecord;
import com.ruoyi.project.ci.service.IPostLikeRecordService;

/**
 * 帖子点赞记录Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PostLikeRecordServiceImpl implements IPostLikeRecordService {
    @Autowired
    private PostLikeRecordMapper postLikeRecordMapper;

    /**
     * 查询帖子点赞记录
     *
     * @param id 帖子点赞记录ID
     * @return 帖子点赞记录
     */
    @Override
    public PostLikeRecord selectPostLikeRecordById(Long id) {
        return postLikeRecordMapper.selectPostLikeRecordById(id);
    }

    /**
     * 查询帖子点赞记录列表
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 帖子点赞记录
     */
    @Override
    public List<PostLikeRecord> selectPostLikeRecordList(PostLikeRecord postLikeRecord) {
        return postLikeRecordMapper.selectPostLikeRecordList(postLikeRecord);
    }

    /**
     * 查询帖子点赞记录选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostLikeRecordOptions(PostLikeRecord postLikeRecord) {
        return postLikeRecordMapper.selectPostLikeRecordOptions(postLikeRecord);
    }

    /**
     * 新增帖子点赞记录
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 结果
     */
    @Override
    public int insertPostLikeRecord(PostLikeRecord postLikeRecord) {
        postLikeRecord.preInsert();
        return postLikeRecordMapper.insertPostLikeRecord(postLikeRecord);
    }

    /**
     * 修改帖子点赞记录
     *
     * @param postLikeRecord 帖子点赞记录
     * @return 结果
     */
    @Override
    public int updatePostLikeRecord(PostLikeRecord postLikeRecord) {
        postLikeRecord.preUpdate();
        return postLikeRecordMapper.updatePostLikeRecord(postLikeRecord);
    }

    /**
     * 批量删除帖子点赞记录
     *
     * @param ids 需要删除的帖子点赞记录ID
     * @return 结果
     */
    @Override
    public int deletePostLikeRecordByIds(Long[] ids) {
        return postLikeRecordMapper.deletePostLikeRecordByIds(ids);
    }

    /**
     * 删除帖子点赞记录信息
     *
     * @param id 帖子点赞记录ID
     * @return 结果
     */
    @Override
    public int deletePostLikeRecordById(Long id) {
        return postLikeRecordMapper.deletePostLikeRecordById(id);
    }
}
