package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CollectionDirectoryMapper;
import com.ruoyi.project.ci.domain.CollectionDirectory;
import com.ruoyi.project.ci.service.ICollectionDirectoryService;

/**
 * 收藏夹Service业务层处理
 *
 * @author sun
 * @date 2020-06-11
 */
@Service
public class CollectionDirectoryServiceImpl implements ICollectionDirectoryService {
    @Autowired
    private CollectionDirectoryMapper collectionDirectoryMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询收藏夹
     *
     * @param id 收藏夹ID
     * @return 收藏夹
     */
    @Override
    public CollectionDirectory selectCollectionDirectoryById(Long id) {
        return collectionDirectoryMapper.selectCollectionDirectoryById(id);
    }

    /**
     * 查询收藏夹列表
     *
     * @param collectionDirectory 收藏夹
     * @return 收藏夹
     */
    @Override
    public List<CollectionDirectory> selectCollectionDirectoryList(CollectionDirectory collectionDirectory) {
        return collectionDirectoryMapper.selectCollectionDirectoryList(collectionDirectory);
    }

    /**
     * 查询收藏夹选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectCollectionDirectoryOptions(CollectionDirectory collectionDirectory) {
        return collectionDirectoryMapper.selectCollectionDirectoryOptions(collectionDirectory);
    }

    /**
     * 新增收藏夹
     *
     * @param collectionDirectory 收藏夹
     * @return 结果
     */
    @Override
    public int insertCollectionDirectory(CollectionDirectory collectionDirectory) {
        collectionDirectory.preInsert();
        return collectionDirectoryMapper.insertCollectionDirectory(collectionDirectory);
    }

    /**
     * 修改收藏夹
     *
     * @param collectionDirectory 收藏夹
     * @return 结果
     */
    @Override
    public int updateCollectionDirectory(CollectionDirectory collectionDirectory) {
        collectionDirectory.preUpdate();
        return collectionDirectoryMapper.updateCollectionDirectory(collectionDirectory);
    }

    /**
     * 批量删除收藏夹
     *
     * @param ids 需要删除的收藏夹ID
     * @return 结果
     */
    @Override
    public int deleteCollectionDirectoryByIds(Long[] ids) {
        return collectionDirectoryMapper.deleteCollectionDirectoryByIds(ids);
    }

    /**
     * 删除收藏夹信息
     *
     * @param id 收藏夹ID
     * @return 结果
     */
    @Override
    public int deleteCollectionDirectoryById(Long id) {
        return collectionDirectoryMapper.deleteCollectionDirectoryById(id);
    }

    /**
     * 注入创建者
     * @param collectionDirectory 注入对象
     */
    private void setCreated(CollectionDirectory collectionDirectory) {
        if(collectionDirectory == null) return;
        if (StringUtils.isNotEmpty(collectionDirectory.getCreateBy())) {
           // collectionDirectory.setCreateUser(userMapper.selectCreateUser(collectionDirectory.getCreateBy()));
        }
    }
}
