package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CollectionContentMapper;
import com.ruoyi.project.ci.domain.CollectionContent;
import com.ruoyi.project.ci.service.ICollectionContentService;

/**
 * 收藏内容Service业务层处理
 *
 * @author sun
 * @date 2020-06-11
 */
@Service
public class CollectionContentServiceImpl implements ICollectionContentService {
    @Autowired
    private CollectionContentMapper collectionContentMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询收藏内容
     *
     * @param id 收藏内容ID
     * @return 收藏内容
     */
    @Override
    public CollectionContent selectCollectionContentById(Long id) {
        return collectionContentMapper.selectCollectionContentById(id);
    }

    /**
     * 查询收藏内容列表
     *
     * @param collectionContent 收藏内容
     * @return 收藏内容
     */
    @Override
    public List<CollectionContent> selectCollectionContentList(CollectionContent collectionContent) {
        return collectionContentMapper.selectCollectionContentList(collectionContent);
    }

    /**
     * 查询收藏内容选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectCollectionContentOptions(CollectionContent collectionContent) {
        return collectionContentMapper.selectCollectionContentOptions(collectionContent);
    }

    /**
     * 新增收藏内容
     *
     * @param collectionContent 收藏内容
     * @return 结果
     */
    @Override
    public CollectionContent insertCollectionContent(CollectionContent collectionContent) {
        collectionContent.preInsert();
        int res = collectionContentMapper.insertCollectionContent(collectionContent);
        return collectionContent;
    }

    /**
     * 修改收藏内容
     *
     * @param collectionContent 收藏内容
     * @return 结果
     */
    @Override
    public int updateCollectionContent(CollectionContent collectionContent) {
        collectionContent.preUpdate();
        return collectionContentMapper.updateCollectionContent(collectionContent);
    }

    /**
     * 批量删除收藏内容
     *
     * @param ids 需要删除的收藏内容ID
     * @return 结果
     */
    @Override
    public int deleteCollectionContentByIds(Long[] ids) {
        return collectionContentMapper.deleteCollectionContentByIds(ids);
    }

    /**
     * 删除收藏内容信息
     *
     * @param id 收藏内容ID
     * @return 结果
     */
    @Override
    public int deleteCollectionContentById(Long id) {
        return collectionContentMapper.deleteCollectionContentById(id);
    }

    /**
     * 注入创建者
     * @param collectionContent 注入对象
     */
    private void setCreated(CollectionContent collectionContent) {
        if(collectionContent == null) return;
        if (StringUtils.isNotEmpty(collectionContent.getCreateBy())) {
           // collectionContent.setCreateUser(userMapper.selectCreateUser(collectionContent.getCreateBy()));
        }
    }
}
