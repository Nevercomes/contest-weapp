package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.CollectionContent;

/**
 * 收藏内容Service接口
 *
 * @author sun
 * @date 2020-06-11
 */
public interface ICollectionContentService {
    /**
     * 查询收藏内容
     *
     * @param id 收藏内容ID
     * @return 收藏内容
     */
    CollectionContent selectCollectionContentById(Long id);

    /**
     * 查询收藏内容列表
     *
     * @param collectionContent 收藏内容
     * @return 收藏内容集合
     */
    List<CollectionContent> selectCollectionContentList(CollectionContent collectionContent);

    /**
     * 查询收藏内容选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectCollectionContentOptions(CollectionContent collectionContent);

    /**
     * 新增收藏内容
     *
     * @param collectionContent 收藏内容
     * @return 结果
     */
    CollectionContent insertCollectionContent(CollectionContent collectionContent);

    /**
     * 修改收藏内容
     *
     * @param collectionContent 收藏内容
     * @return 结果
     */
    int updateCollectionContent(CollectionContent collectionContent);

    /**
     * 批量删除收藏内容
     *
     * @param ids 需要删除的收藏内容ID
     * @return 结果
     */
    int deleteCollectionContentByIds(Long[] ids);

    /**
     * 删除收藏内容信息
     *
     * @param id 收藏内容ID
     * @return 结果
     */
    int deleteCollectionContentById(Long id);
}
