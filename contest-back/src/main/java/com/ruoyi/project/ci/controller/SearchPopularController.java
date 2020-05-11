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
import com.ruoyi.project.ci.domain.SearchPopular;
import com.ruoyi.project.ci.service.ISearchPopularService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * popularController
 *
 * @author sun
 * @date 2020-05-05
 */
@RestController
@RequestMapping("/ci/popular")
public class SearchPopularController extends BaseController {
    @Autowired
    private ISearchPopularService searchPopularService;

    /**
     * 查询popular列表
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:list')")
    @GetMapping("/list")
    public TableDataInfo list(SearchPopular searchPopular) {
        startPage();
        List<SearchPopular> list = searchPopularService.selectSearchPopularList(searchPopular);
        return getDataTable(list);
    }

    /**
     * 导出popular列表
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:export')")
    @Log(title = "popular", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SearchPopular searchPopular) {
        List<SearchPopular> list = searchPopularService.selectSearchPopularList(searchPopular);
        ExcelUtil<SearchPopular> util = new ExcelUtil<SearchPopular>(SearchPopular.class);
        return util.exportExcel(list, "popular");
    }

    /**
     * 获取popular详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(searchPopularService.selectSearchPopularById(id));
    }

    /**
     * 获取popular选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(SearchPopular searchPopular) {
        return AjaxResult.success(searchPopularService.selectSearchPopularOptions(searchPopular));
    }

    /**
     * 新增popular
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:add')")
    @Log(title = "popular", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SearchPopular searchPopular) {
        return toAjax(searchPopularService.insertSearchPopular(searchPopular));
    }

    /**
     * 修改popular
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:edit')")
    @Log(title = "popular", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SearchPopular searchPopular) {
        return toAjax(searchPopularService.updateSearchPopular(searchPopular));
    }

    /**
     * 删除popular
     */
    @PreAuthorize("@ss.hasPermi('ci:popular:remove')")
    @Log(title = "popular", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(searchPopularService.deleteSearchPopularByIds(ids));
    }
}
