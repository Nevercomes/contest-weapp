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
import com.ruoyi.project.ci.domain.RecommendPost;
import com.ruoyi.project.ci.service.IRecommendPostService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子推荐Controller
 *
 * @author sun
 * @date 2020-05-31
 */
@RestController
@RequestMapping("/ci/recoPost")
public class RecommendPostController extends BaseController {
    @Autowired
    private IRecommendPostService recommendPostService;

    /**
     * 查询帖子推荐列表
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecommendPost recommendPost) {
        startPage();
        List<RecommendPost> list = recommendPostService.selectRecommendPostList(recommendPost);
        return getDataTable(list);
    }

    /**
     * 导出帖子推荐列表
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:export')")
    @Log(title = "帖子推荐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RecommendPost recommendPost) {
        List<RecommendPost> list = recommendPostService.selectRecommendPostList(recommendPost);
        ExcelUtil<RecommendPost> util = new ExcelUtil<RecommendPost>(RecommendPost.class);
        return util.exportExcel(list, "recoPost");
    }

    /**
     * 获取帖子推荐详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(recommendPostService.selectRecommendPostById(id));
    }

    /**
     * 获取帖子推荐选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(RecommendPost recommendPost) {
        return AjaxResult.success(recommendPostService.selectRecommendPostOptions(recommendPost));
    }

    /**
     * 新增帖子推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:add')")
    @Log(title = "帖子推荐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecommendPost recommendPost) {
        return toAjax(recommendPostService.insertRecommendPost(recommendPost));
    }

    /**
     * 修改帖子推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:edit')")
    @Log(title = "帖子推荐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecommendPost recommendPost) {
        return toAjax(recommendPostService.updateRecommendPost(recommendPost));
    }

    /**
     * 删除帖子推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoPost:remove')")
    @Log(title = "帖子推荐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(recommendPostService.deleteRecommendPostByIds(ids));
    }
}
