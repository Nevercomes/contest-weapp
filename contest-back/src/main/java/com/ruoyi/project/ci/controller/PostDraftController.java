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
import com.ruoyi.project.ci.domain.PostDraft;
import com.ruoyi.project.ci.service.IPostDraftService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子草稿Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/draft")
public class PostDraftController extends BaseController {
    @Autowired
    private IPostDraftService postDraftService;

    /**
     * 查询帖子草稿列表
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:list')")
    @GetMapping("/list")
    public TableDataInfo list(PostDraft postDraft) {
        startPage();
        List<PostDraft> list = postDraftService.selectPostDraftList(postDraft);
        return getDataTable(list);
    }

    /**
     * 导出帖子草稿列表
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:export')")
    @Log(title = "帖子草稿", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PostDraft postDraft) {
        List<PostDraft> list = postDraftService.selectPostDraftList(postDraft);
        ExcelUtil<PostDraft> util = new ExcelUtil<PostDraft>(PostDraft.class);
        return util.exportExcel(list, "draft");
    }

    /**
     * 获取帖子草稿详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(postDraftService.selectPostDraftById(id));
    }

    /**
     * 获取帖子草稿选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PostDraft postDraft) {
        return AjaxResult.success(postDraftService.selectPostDraftOptions(postDraft));
    }

    /**
     * 新增帖子草稿
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:add')")
    @Log(title = "帖子草稿", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostDraft postDraft) {
        return toAjax(postDraftService.insertPostDraft(postDraft));
    }

    /**
     * 修改帖子草稿
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:edit')")
    @Log(title = "帖子草稿", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostDraft postDraft) {
        return toAjax(postDraftService.updatePostDraft(postDraft));
    }

    /**
     * 删除帖子草稿
     */
    @PreAuthorize("@ss.hasPermi('ci:draft:remove')")
    @Log(title = "帖子草稿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(postDraftService.deletePostDraftByIds(ids));
    }
}
