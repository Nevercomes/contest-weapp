package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.constant.DictConstant;
import com.ruoyi.common.utils.StringUtils;
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
        if (StringUtils.isEmpty(postInfo.getStatus())) {
            postInfo.setStatus(DictConstant.POST_STATUS_PUBLIC);
        }
        List<PostInfo> list = postInfoService.selectPostInfoList(postInfo);
        return getDataTable(list);
    }

    /**
     * 查询自己的草稿
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:list')")
    @GetMapping("/list/draft")
    public TableDataInfo listDraft(PostInfo postInfo) {
        startPage();
        listSelf(postInfo);
        postInfo.setStatus(DictConstant.POST_STATUS_DRAFT);
        List<PostInfo> list = postInfoService.selectPostInfoList(postInfo);
        return getDataTable(list);
    }

    /**
     * 查询自己发布的内容
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:list')")
    @GetMapping("/list/public")
    public TableDataInfo listPublic(PostInfo postInfo) {
        startPage();
        listSelf(postInfo);
        postInfo.setStatus(DictConstant.POST_STATUS_PUBLIC);
        List<PostInfo> list = postInfoService.selectPostInfoList(postInfo);
        return getDataTable(list);
    }

    /**
     * 查询自己发布的内容
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:list')")
    @GetMapping("/list/collection")
    public TableDataInfo listCollection(PostInfo postInfo) {
        startPage();
        listSelf(postInfo);
        List<PostInfo> list = postInfoService.selectPostInfoCollection(postInfo);
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
        PostInfo postInfo = postInfoService.selectPostInfoById(id);
        if (DictConstant.POST_STATUS_PUBLIC.equals(postInfo.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordPostView(postInfo));
        }
        return AjaxResult.success(postInfo);
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
     * 发布帖子
     */
    @PreAuthorize("@ss.hasPermi('ci:postInfo:add')")
    @Log(title = "帖子信息", businessType = BusinessType.INSERT)
    @PutMapping("/public/{postId}")
    public AjaxResult publicPost(@PathVariable("postId") Long postId) {
        return toAjax(postInfoService.publicPost(postId));
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
