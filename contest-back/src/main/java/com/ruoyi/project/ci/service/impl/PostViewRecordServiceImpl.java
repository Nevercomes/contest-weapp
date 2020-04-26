package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PostViewRecordMapper;
import com.ruoyi.project.ci.domain.PostViewRecord;
import com.ruoyi.project.ci.service.IPostViewRecordService;

/**
 * 帖子浏览记录Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PostViewRecordServiceImpl implements IPostViewRecordService {
    @Autowired
    private PostViewRecordMapper postViewRecordMapper;

    /**
     * 查询帖子浏览记录
     *
     * @param id 帖子浏览记录ID
     * @return 帖子浏览记录
     */
    @Override
    public PostViewRecord selectPostViewRecordById(Long id) {
        return postViewRecordMapper.selectPostViewRecordById(id);
    }

    /**
     * 查询帖子浏览记录列表
     *
     * @param postViewRecord 帖子浏览记录
     * @return 帖子浏览记录
     */
    @Override
    public List<PostViewRecord> selectPostViewRecordList(PostViewRecord postViewRecord) {
        return postViewRecordMapper.selectPostViewRecordList(postViewRecord);
    }

    /**
     * 查询帖子浏览记录选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPostViewRecordOptions(PostViewRecord postViewRecord) {
        return postViewRecordMapper.selectPostViewRecordOptions(postViewRecord);
    }

    /**
     * 新增帖子浏览记录
     *
     * @param postViewRecord 帖子浏览记录
     * @return 结果
     */
    @Override
    public int insertPostViewRecord(PostViewRecord postViewRecord) {
        postViewRecord.preInsert();
        return postViewRecordMapper.insertPostViewRecord(postViewRecord);
    }

    /**
     * 修改帖子浏览记录
     *
     * @param postViewRecord 帖子浏览记录
     * @return 结果
     */
    @Override
    public int updatePostViewRecord(PostViewRecord postViewRecord) {
        postViewRecord.preUpdate();
        return postViewRecordMapper.updatePostViewRecord(postViewRecord);
    }

    /**
     * 批量删除帖子浏览记录
     *
     * @param ids 需要删除的帖子浏览记录ID
     * @return 结果
     */
    @Override
    public int deletePostViewRecordByIds(Long[] ids) {
        return postViewRecordMapper.deletePostViewRecordByIds(ids);
    }

    /**
     * 删除帖子浏览记录信息
     *
     * @param id 帖子浏览记录ID
     * @return 结果
     */
    @Override
    public int deletePostViewRecordById(Long id) {
        return postViewRecordMapper.deletePostViewRecordById(id);
    }
}
