package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.SearchRecordMapper;
import com.ruoyi.project.ci.domain.SearchRecord;
import com.ruoyi.project.ci.service.ISearchRecordService;

/**
 * searchRecordService业务层处理
 *
 * @author sun
 * @date 2020-05-05
 */
@Service
public class SearchRecordServiceImpl implements ISearchRecordService {
    @Autowired
    private SearchRecordMapper searchRecordMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询searchRecord
     *
     * @param id searchRecordID
     * @return searchRecord
     */
    @Override
    public SearchRecord selectSearchRecordById(Long id) {
        return searchRecordMapper.selectSearchRecordById(id);
    }

    /**
     * 查询searchRecord列表
     *
     * @param searchRecord searchRecord
     * @return searchRecord
     */
    @Override
    public List<SearchRecord> selectSearchRecordList(SearchRecord searchRecord) {
        return searchRecordMapper.selectSearchRecordList(searchRecord);
    }

    /**
     * 查询searchRecord选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectSearchRecordOptions(SearchRecord searchRecord) {
        return searchRecordMapper.selectSearchRecordOptions(searchRecord);
    }

    /**
     * 新增searchRecord
     *
     * @param searchRecord searchRecord
     * @return 结果
     */
    @Override
    public int insertSearchRecord(SearchRecord searchRecord) {
        searchRecord.preInsert();
        return searchRecordMapper.insertSearchRecord(searchRecord);
    }

    /**
     * 修改searchRecord
     *
     * @param searchRecord searchRecord
     * @return 结果
     */
    @Override
    public int updateSearchRecord(SearchRecord searchRecord) {
        searchRecord.preUpdate();
        return searchRecordMapper.updateSearchRecord(searchRecord);
    }

    /**
     * 批量删除searchRecord
     *
     * @param ids 需要删除的searchRecordID
     * @return 结果
     */
    @Override
    public int deleteSearchRecordByIds(Long[] ids) {
        return searchRecordMapper.deleteSearchRecordByIds(ids);
    }

    /**
     * 删除searchRecord信息
     *
     * @param id searchRecordID
     * @return 结果
     */
    @Override
    public int deleteSearchRecordById(Long id) {
        return searchRecordMapper.deleteSearchRecordById(id);
    }

    @Override
    public int deleteSearchRecordByUsername(SearchRecord q) {
        return searchRecordMapper.deleteSearchRecordByUsername(q);
    }

    /**
     * 注入创建者
     * @param searchRecord 注入对象
     */
    private void setCreated(SearchRecord searchRecord) {
        if(searchRecord == null) return;
        if (StringUtils.isNotEmpty(searchRecord.getCreateBy())) {
           // searchRecord.setCreateUser(userMapper.selectCreateUser(searchRecord.getCreateBy()));
        }
    }
}
