package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
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
import com.ruoyi.project.ci.domain.CollectionContent;
import com.ruoyi.project.ci.service.ICollectionContentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 收藏内容Controller
 *
 * @author sun
 * @date 2020-06-11
 */
@RestController
@RequestMapping("/ci/collection")
public class CollectionContentController extends BaseController {
    @Autowired
    private ICollectionContentService collectionContentService;

    /**
     * 查询收藏内容列表
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:list')")
    @GetMapping("/list")
    public TableDataInfo list(CollectionContent collectionContent) {
        startPage();
        listSelf(collectionContent);
        List<CollectionContent> list = collectionContentService.selectCollectionContentList(collectionContent);
        return getDataTable(list);
    }

    /**
     * 导出收藏内容列表
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:export')")
    @Log(title = "收藏内容", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CollectionContent collectionContent) {
        List<CollectionContent> list = collectionContentService.selectCollectionContentList(collectionContent);
        ExcelUtil<CollectionContent> util = new ExcelUtil<CollectionContent>(CollectionContent.class);
        return util.exportExcel(list, "collection");
    }

    /**
     * 获取收藏内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(collectionContentService.selectCollectionContentById(id));
    }

    /**
     * 获取收藏内容选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(CollectionContent collectionContent) {
        return AjaxResult.success(collectionContentService.selectCollectionContentOptions(collectionContent));
    }

    /**
     * 新增收藏内容
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:add')")
    @Log(title = "收藏内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CollectionContent collectionContent) {
        CollectionContent res = collectionContentService.insertCollectionContent(collectionContent);
        // 异步增加收藏数目
        AsyncManager.me().execute(AsyncFactory.recordPostCollect(collectionContent));
        return AjaxResult.success(res);
    }

    /**
     * 修改收藏内容
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:edit')")
    @Log(title = "收藏内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CollectionContent collectionContent) {
        return toAjax(collectionContentService.updateCollectionContent(collectionContent));
    }

    /**
     * 删除收藏内容
     */
    @PreAuthorize("@ss.hasPermi('ci:collection:remove')")
    @Log(title = "收藏内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(collectionContentService.deleteCollectionContentByIds(ids));
    }
}
