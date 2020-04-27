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
import com.ruoyi.project.ci.domain.MsgSysNotice;
import com.ruoyi.project.ci.service.IMsgSysNoticeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 系统消息Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/notice")
public class MsgSysNoticeController extends BaseController {
    @Autowired
    private IMsgSysNoticeService msgSysNoticeService;

    /**
     * 查询系统消息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(MsgSysNotice msgSysNotice) {
        startPage();
        List<MsgSysNotice> list = msgSysNoticeService.selectMsgSysNoticeList(msgSysNotice);
        return getDataTable(list);
    }

    /**
     * 导出系统消息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:export')")
    @Log(title = "系统消息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MsgSysNotice msgSysNotice) {
        List<MsgSysNotice> list = msgSysNoticeService.selectMsgSysNoticeList(msgSysNotice);
        ExcelUtil<MsgSysNotice> util = new ExcelUtil<MsgSysNotice>(MsgSysNotice.class);
        return util.exportExcel(list, "notice");
    }

    /**
     * 获取系统消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(msgSysNoticeService.selectMsgSysNoticeById(id));
    }

    /**
     * 获取系统消息选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(MsgSysNotice msgSysNotice) {
        return AjaxResult.success(msgSysNoticeService.selectMsgSysNoticeOptions(msgSysNotice));
    }

    /**
     * 新增系统消息
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:add')")
    @Log(title = "系统消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MsgSysNotice msgSysNotice) {
        return toAjax(msgSysNoticeService.insertMsgSysNotice(msgSysNotice));
    }

    /**
     * 修改系统消息
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:edit')")
    @Log(title = "系统消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MsgSysNotice msgSysNotice) {
        return toAjax(msgSysNoticeService.updateMsgSysNotice(msgSysNotice));
    }

    /**
     * 删除系统消息
     */
    @PreAuthorize("@ss.hasPermi('ci:notice:remove')")
    @Log(title = "系统消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(msgSysNoticeService.deleteMsgSysNoticeByIds(ids));
    }
}
