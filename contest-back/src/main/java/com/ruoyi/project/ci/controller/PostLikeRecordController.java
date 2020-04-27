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
import com.ruoyi.project.ci.domain.PostLikeRecord;
import com.ruoyi.project.ci.service.IPostLikeRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子点赞记录Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/postLIke")
public class PostLikeRecordController extends BaseController {
    @Autowired
    private IPostLikeRecordService postLikeRecordService;

    /**
     * 查询帖子点赞记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:list')")
    @GetMapping("/list")
    public TableDataInfo list(PostLikeRecord postLikeRecord) {
        startPage();
        List<PostLikeRecord> list = postLikeRecordService.selectPostLikeRecordList(postLikeRecord);
        return getDataTable(list);
    }

    /**
     * 导出帖子点赞记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:export')")
    @Log(title = "帖子点赞记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PostLikeRecord postLikeRecord) {
        List<PostLikeRecord> list = postLikeRecordService.selectPostLikeRecordList(postLikeRecord);
        ExcelUtil<PostLikeRecord> util = new ExcelUtil<PostLikeRecord>(PostLikeRecord.class);
        return util.exportExcel(list, "postLIke");
    }

    /**
     * 获取帖子点赞记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(postLikeRecordService.selectPostLikeRecordById(id));
    }

    /**
     * 获取帖子点赞记录选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PostLikeRecord postLikeRecord) {
        return AjaxResult.success(postLikeRecordService.selectPostLikeRecordOptions(postLikeRecord));
    }

    /**
     * 新增帖子点赞记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:add')")
    @Log(title = "帖子点赞记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostLikeRecord postLikeRecord) {
        return toAjax(postLikeRecordService.insertPostLikeRecord(postLikeRecord));
    }

    /**
     * 修改帖子点赞记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:edit')")
    @Log(title = "帖子点赞记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostLikeRecord postLikeRecord) {
        return toAjax(postLikeRecordService.updatePostLikeRecord(postLikeRecord));
    }

    /**
     * 删除帖子点赞记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postLIke:remove')")
    @Log(title = "帖子点赞记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(postLikeRecordService.deletePostLikeRecordByIds(ids));
    }
}
