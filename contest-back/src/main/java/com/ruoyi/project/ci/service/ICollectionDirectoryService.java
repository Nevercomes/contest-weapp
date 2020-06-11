package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.CollectionDirectory;

/**
 * 收藏夹Service接口
 *
 * @author sun
 * @date 2020-06-11
 */
public interface ICollectionDirectoryService {
    /**
     * 查询收藏夹
     *
     * @param id 收藏夹ID
     * @return 收藏夹
     */
    CollectionDirectory selectCollectionDirectoryById(Long id);

    /**
     * 查询收藏夹列表
     *
     * @param collectionDirectory 收藏夹
     * @return 收藏夹集合
     */
    List<CollectionDirectory> selectCollectionDirectoryList(CollectionDirectory collectionDirectory);

    /**
     * 查询收藏夹选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectCollectionDirectoryOptions(CollectionDirectory collectionDirectory);

    /**
     * 新增收藏夹
     *
     * @param collectionDirectory 收藏夹
     * @return 结果
     */
    int insertCollectionDirectory(CollectionDirectory collectionDirectory);

    /**
     * 修改收藏夹
     *
     * @param collectionDirectory 收藏夹
     * @return 结果
     */
    int updateCollectionDirectory(CollectionDirectory collectionDirectory);

    /**
     * 批量删除收藏夹
     *
     * @param ids 需要删除的收藏夹ID
     * @return 结果
     */
    int deleteCollectionDirectoryByIds(Long[] ids);

    /**
     * 删除收藏夹信息
     *
     * @param id 收藏夹ID
     * @return 结果
     */
    int deleteCollectionDirectoryById(Long id);
}
