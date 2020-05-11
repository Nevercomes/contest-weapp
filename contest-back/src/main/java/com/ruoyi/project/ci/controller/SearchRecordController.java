package com.ruoyi.project.ci.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.ci.domain.SearchRecord;
import com.ruoyi.project.ci.service.ISearchRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * searchRecordController
 *
 * @author sun
 * @date 2020-05-05
 */
@RestController
@RequestMapping("/ci/searchRecord")
public class SearchRecordController extends BaseController {
    @Autowired
    private ISearchRecordService searchRecordService;

    /**
     * 查询searchRecord列表
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SearchRecord searchRecord) {
        startPage();
        List<SearchRecord> list = searchRecordService.selectSearchRecordList(searchRecord);
        return getDataTable(list);
    }

    /**
     * 导出searchRecord列表
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:export')")
    @Log(title = "searchRecord", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SearchRecord searchRecord) {
        List<SearchRecord> list = searchRecordService.selectSearchRecordList(searchRecord);
        ExcelUtil<SearchRecord> util = new ExcelUtil<SearchRecord>(SearchRecord.class);
        return util.exportExcel(list, "searchRecord");
    }

    /**
     * 获取searchRecord详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(searchRecordService.selectSearchRecordById(id));
    }

    /**
     * 获取searchRecord选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(SearchRecord searchRecord) {
        return AjaxResult.success(searchRecordService.selectSearchRecordOptions(searchRecord));
    }

    /**
     * 新增searchRecord
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:add')")
    @Log(title = "searchRecord", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SearchRecord searchRecord) {
        return toAjax(searchRecordService.insertSearchRecord(searchRecord));
    }

    /**
     * 修改searchRecord
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:edit')")
    @Log(title = "searchRecord", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SearchRecord searchRecord) {
        return toAjax(searchRecordService.updateSearchRecord(searchRecord));
    }

    /**
     * 删除searchRecord
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:remove')")
    @Log(title = "searchRecord", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(searchRecordService.deleteSearchRecordByIds(ids));
    }
}
