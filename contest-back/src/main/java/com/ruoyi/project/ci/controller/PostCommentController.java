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
import com.ruoyi.project.ci.domain.PostComment;
import com.ruoyi.project.ci.service.IPostCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子评论Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/comment")
public class PostCommentController extends BaseController {
    @Autowired
    private IPostCommentService postCommentService;

    /**
     * 查询帖子评论列表
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(PostComment postComment) {
        startPage();
        List<PostComment> list = postCommentService.selectPostCommentList(postComment);
        return getDataTable(list);
    }

    /**
     * 导出帖子评论列表
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:export')")
    @Log(title = "帖子评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PostComment postComment) {
        List<PostComment> list = postCommentService.selectPostCommentList(postComment);
        ExcelUtil<PostComment> util = new ExcelUtil<PostComment>(PostComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取帖子评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(postCommentService.selectPostCommentById(id));
    }

    /**
     * 获取帖子评论选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PostComment postComment) {
        return AjaxResult.success(postCommentService.selectPostCommentOptions(postComment));
    }

    /**
     * 新增帖子评论
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:add')")
    @Log(title = "帖子评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostComment postComment) {
        return toAjax(postCommentService.insertPostComment(postComment));
    }

    /**
     * 修改帖子评论
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:edit')")
    @Log(title = "帖子评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostComment postComment) {
        return toAjax(postCommentService.updatePostComment(postComment));
    }

    /**
     * 删除帖子评论
     */
    @PreAuthorize("@ss.hasPermi('ci:comment:remove')")
    @Log(title = "帖子评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(postCommentService.deletePostCommentByIds(ids));
    }
}
