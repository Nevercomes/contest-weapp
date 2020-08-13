package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ci.domain.SearchRecord;

/**
 * searchRecordService接口
 *
 * @author sun
 * @date 2020-05-05
 */
public interface ISearchRecordService {
    /**
     * 查询searchRecord
     *
     * @param id searchRecordID
     * @return searchRecord
     */
    SearchRecord selectSearchRecordById(Long id);

    /**
     * 查询searchRecord列表
     *
     * @param searchRecord searchRecord
     * @return searchRecord集合
     */
    List<SearchRecord> selectSearchRecordList(SearchRecord searchRecord);

    /**
     * 查询searchRecord选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectSearchRecordOptions(SearchRecord searchRecord);

    /**
     * 新增searchRecord
     *
     * @param searchRecord searchRecord
     * @return 结果
     */
    int insertSearchRecord(SearchRecord searchRecord);

    /**
     * 修改searchRecord
     *
     * @param searchRecord searchRecord
     * @return 结果
     */
    int updateSearchRecord(SearchRecord searchRecord);

    /**
     * 批量删除searchRecord
     *
     * @param ids 需要删除的searchRecordID
     * @return 结果
     */
    int deleteSearchRecordByIds(Long[] ids);

    /**
     * 删除searchRecord信息
     *
     * @param id searchRecordID
     * @return 结果
     */
    int deleteSearchRecordById(Long id);

    /**
     * 删除个人的搜索记录
     * @param q
     * @return
     */
    int deleteSearchRecordByUsername(SearchRecord q);
}
