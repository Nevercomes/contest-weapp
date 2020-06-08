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
import com.ruoyi.project.ci.domain.PostInfo;
import com.ruoyi.project.ci.service.IPostInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 帖子信息Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/postInfo")
public class PostInfoController extends BaseController {
    @Autowired
    private IPostInfoService postInfoService;

    /**
     * 查询帖子信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(PostInfo postInfo) {
        startPage();
        List<PostInfo> list = postInfoService.selectPostInfoList(postInfo);
        return getDataTable(list);
    }

    /**
     * 导出帖子信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:export')")
    @Log(title = "帖子信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PostInfo postInfo) {
        List<PostInfo> list = postInfoService.selectPostInfoList(postInfo);
        ExcelUtil<PostInfo> util = new ExcelUtil<PostInfo>(PostInfo.class);
        return util.exportExcel(list, "postInfo");
    }

    /**
     * 获取帖子信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(postInfoService.selectPostInfoById(id));
    }

    /**
     * 获取帖子信息选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PostInfo postInfo) {
        return AjaxResult.success(postInfoService.selectPostInfoOptions(postInfo));
    }

    /**
     * 新增帖子信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:add')")
    @Log(title = "帖子信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostInfo postInfo) {
        postInfo = postInfoService.insertPostInfo(postInfo);
        return AjaxResult.success(postInfo);
    }

    /**
     * 修改帖子信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:edit')")
    @Log(title = "帖子信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostInfo postInfo) {
        postInfo = postInfoService.updatePostInfo(postInfo);
        return AjaxResult.success(postInfo);
    }

    /**
     * 删除帖子信息
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:remove')")
    @Log(title = "帖子信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(postInfoService.deletePostInfoByIds(ids));
    }
}
