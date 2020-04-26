package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PostInfo;

/**
 * 帖子信息Mapper接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface PostInfoMapper {
    /**
     * 查询帖子信息
     *
     * @param id 帖子信息ID
     * @return 帖子信息
     */
    PostInfo selectPostInfoById(Long id);

    /**
     * 查询帖子信息列表
     *
     * @param postInfo 帖子信息
     * @return 帖子信息集合
     */
    List<PostInfo> selectPostInfoList(PostInfo postInfo);

    /**
     * 查询帖子信息选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPostInfoOptions(PostInfo postInfo);

    /**
     * 新增帖子信息
     *
     * @param postInfo 帖子信息
     * @return 结果
     */
    int insertPostInfo(PostInfo postInfo);

    /**
     * 修改帖子信息
     *
     * @param postInfo 帖子信息
     * @return 结果
     */
    int updatePostInfo(PostInfo postInfo);

    /**
     * 删除帖子信息
     *
     * @param id 帖子信息ID
     * @return 结果
     */
    int deletePostInfoById(Long id);

    /**
     * 批量删除帖子信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePostInfoByIds(Long[] ids);
}
