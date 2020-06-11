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
import com.ruoyi.project.ci.domain.CollectionDirectory;
import com.ruoyi.project.ci.service.ICollectionDirectoryService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 收藏夹Controller
 *
 * @author sun
 * @date 2020-06-11
 */
@RestController
@RequestMapping("/ci/directory")
public class CollectionDirectoryController extends BaseController {
    @Autowired
    private ICollectionDirectoryService collectionDirectoryService;

    /**
     * 查询收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:list')")
    @GetMapping("/list")
    public TableDataInfo list(CollectionDirectory collectionDirectory) {
        startPage();
        List<CollectionDirectory> list = collectionDirectoryService.selectCollectionDirectoryList(collectionDirectory);
        return getDataTable(list);
    }

    /**
     * 导出收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:export')")
    @Log(title = "收藏夹", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CollectionDirectory collectionDirectory) {
        List<CollectionDirectory> list = collectionDirectoryService.selectCollectionDirectoryList(collectionDirectory);
        ExcelUtil<CollectionDirectory> util = new ExcelUtil<CollectionDirectory>(CollectionDirectory.class);
        return util.exportExcel(list, "directory");
    }

    /**
     * 获取收藏夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(collectionDirectoryService.selectCollectionDirectoryById(id));
    }

    /**
     * 获取收藏夹选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(CollectionDirectory collectionDirectory) {
        return AjaxResult.success(collectionDirectoryService.selectCollectionDirectoryOptions(collectionDirectory));
    }

    /**
     * 新增收藏夹
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:add')")
    @Log(title = "收藏夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CollectionDirectory collectionDirectory) {
        return toAjax(collectionDirectoryService.insertCollectionDirectory(collectionDirectory));
    }

    /**
     * 修改收藏夹
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:edit')")
    @Log(title = "收藏夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CollectionDirectory collectionDirectory) {
        return toAjax(collectionDirectoryService.updateCollectionDirectory(collectionDirectory));
    }

    /**
     * 删除收藏夹
     */
    @PreAuthorize("@ss.hasPermi('ci:directory:remove')")
    @Log(title = "收藏夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(collectionDirectoryService.deleteCollectionDirectoryByIds(ids));
    }
}
