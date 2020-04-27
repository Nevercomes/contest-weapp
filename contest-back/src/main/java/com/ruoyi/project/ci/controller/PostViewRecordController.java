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
import com.ruoyi.project.ci.domain.PostViewRecord;
import com.ruoyi.project.ci.service.IPostViewRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子浏览记录Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/postView")
public class PostViewRecordController extends BaseController {
    @Autowired
    private IPostViewRecordService postViewRecordService;

    /**
     * 查询帖子浏览记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:list')")
    @GetMapping("/list")
    public TableDataInfo list(PostViewRecord postViewRecord) {
        startPage();
        List<PostViewRecord> list = postViewRecordService.selectPostViewRecordList(postViewRecord);
        return getDataTable(list);
    }

    /**
     * 导出帖子浏览记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:export')")
    @Log(title = "帖子浏览记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PostViewRecord postViewRecord) {
        List<PostViewRecord> list = postViewRecordService.selectPostViewRecordList(postViewRecord);
        ExcelUtil<PostViewRecord> util = new ExcelUtil<PostViewRecord>(PostViewRecord.class);
        return util.exportExcel(list, "postView");
    }

    /**
     * 获取帖子浏览记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(postViewRecordService.selectPostViewRecordById(id));
    }

    /**
     * 获取帖子浏览记录选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PostViewRecord postViewRecord) {
        return AjaxResult.success(postViewRecordService.selectPostViewRecordOptions(postViewRecord));
    }

    /**
     * 新增帖子浏览记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:add')")
    @Log(title = "帖子浏览记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostViewRecord postViewRecord) {
        return toAjax(postViewRecordService.insertPostViewRecord(postViewRecord));
    }

    /**
     * 修改帖子浏览记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:edit')")
    @Log(title = "帖子浏览记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostViewRecord postViewRecord) {
        return toAjax(postViewRecordService.updatePostViewRecord(postViewRecord));
    }

    /**
     * 删除帖子浏览记录
     */
    @PreAuthorize("@ss.hasPermi('ci:postView:remove')")
    @Log(title = "帖子浏览记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(postViewRecordService.deletePostViewRecordByIds(ids));
    }
}
