package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
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
import com.ruoyi.project.ci.domain.MsgLetter;
import com.ruoyi.project.ci.service.IMsgLetterService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 私信管理Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/letter")
public class MsgLetterController extends BaseController {
    @Autowired
    private IMsgLetterService msgLetterService;

    /**
     * 查询私信管理列表
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:list')")
    @GetMapping("/list")
    public TableDataInfo list(MsgLetter msgLetter) {
        startPage();
        List<MsgLetter> list = msgLetterService.selectMsgLetterList(msgLetter);
        return getDataTable(list);
    }

    /**
     * 查询私信管理列表
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:list')")
    @GetMapping("/list/digest")
    public TableDataInfo listDigest(MsgLetter msgLetter) {
        msgLetter.setSendUserId(SecurityUtils.getUserId());
        startPage();
        List<MsgLetter> list = msgLetterService.selectLetterDigest(msgLetter);
        return getDataTable(list);
    }

    /**
     * 查询聊天内容
     * @return
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:list')")
    @GetMapping("/list/content")
    public TableDataInfo listContent(MsgLetter msgLetter) {
        startPage();
        msgLetter.setSendUserId(SecurityUtils.getUserId());
        List<MsgLetter> list = msgLetterService.selectLetterContent(msgLetter);
        return getDataTable(list);
    }

    /**
     * 导出私信管理列表
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:export')")
    @Log(title = "私信管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MsgLetter msgLetter) {
        List<MsgLetter> list = msgLetterService.selectMsgLetterList(msgLetter);
        ExcelUtil<MsgLetter> util = new ExcelUtil<MsgLetter>(MsgLetter.class);
        return util.exportExcel(list, "letter");
    }

    /**
     * 获取私信管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(msgLetterService.selectMsgLetterById(id));
    }

    /**
     * 获取私信管理选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(MsgLetter msgLetter) {
        return AjaxResult.success(msgLetterService.selectMsgLetterOptions(msgLetter));
    }

    /**
     * 新增私信管理
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:add')")
    @Log(title = "私信管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MsgLetter msgLetter) {
        msgLetter.setSendUserId(SecurityUtils.getUserId());
        return AjaxResult.success(msgLetterService.insertMsgLetter(msgLetter));
    }

    /**
     * 修改私信管理
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:edit')")
    @Log(title = "私信管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MsgLetter msgLetter) {
        return toAjax(msgLetterService.updateMsgLetter(msgLetter));
    }

    /**
     * 设置已读
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:edit')")
    @Log(title = "私信管理", businessType = BusinessType.UPDATE)
    @PutMapping("/read")
    public AjaxResult read(@RequestBody MsgLetter msgLetter) {
        msgLetter.setReceiveUserId(SecurityUtils.getUserId());
        msgLetterService.readMsgLetter(msgLetter);
        return AjaxResult.success();
    }

    /**
     * 删除私信管理
     */
    @PreAuthorize("@ss.hasPermi('ci:letter:remove')")
    @Log(title = "私信管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(msgLetterService.deleteMsgLetterByIds(ids));
    }
}
