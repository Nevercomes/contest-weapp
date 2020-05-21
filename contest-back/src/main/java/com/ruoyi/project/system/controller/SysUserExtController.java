package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.system.domain.SysUserExt;
import com.ruoyi.project.system.service.ISysUserExtService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户扩展信息Controller
 *
 * @author sun
 * @date 2020-05-21
 */
@RestController
@RequestMapping("/system/ext")
public class SysUserExtController extends BaseController {
    @Autowired
    private ISysUserExtService sysUserExtService;

    /**
     * 查询用户扩展信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ext:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserExt sysUserExt) {
        startPage();
        List<SysUserExt> list = sysUserExtService.selectSysUserExtList(sysUserExt);
        return getDataTable(list);
    }

    /**
     * 导出用户扩展信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ext:export')")
    @Log(title = "用户扩展信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysUserExt sysUserExt) {
        List<SysUserExt> list = sysUserExtService.selectSysUserExtList(sysUserExt);
        ExcelUtil<SysUserExt> util = new ExcelUtil<SysUserExt>(SysUserExt.class);
        return util.exportExcel(list, "ext");
    }

    /**
     * 获取用户扩展信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ext:query')")
    @GetMapping(value = {"/{userId}", "/"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        if(userId == null) {
            userId = SecurityUtils.getUserId();
        }
        return AjaxResult.success(sysUserExtService.selectSysUserExtById(userId));
    }

    /**
     * 获取用户扩展信息选项信息
     */
    @PreAuthorize("@ss.hasPermi('system:ext:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(SysUserExt sysUserExt) {
        return AjaxResult.success(sysUserExtService.selectSysUserExtOptions(sysUserExt));
    }

    /**
     * 新增用户扩展信息
     */
    @PreAuthorize("@ss.hasPermi('system:ext:add')")
    @Log(title = "用户扩展信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserExt sysUserExt) {
        if (sysUserExt.getUserId() == null) {
            sysUserExt.setUserId(SecurityUtils.getUserId());
        }
        return toAjax(sysUserExtService.insertSysUserExt(sysUserExt));
    }

    /**
     * 修改用户扩展信息
     */
    @PreAuthorize("@ss.hasPermi('system:ext:edit')")
    @Log(title = "用户扩展信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserExt sysUserExt) {
        if (sysUserExt.getUserId() == null) {
            sysUserExt.setUserId(SecurityUtils.getUserId());
        }
        return toAjax(sysUserExtService.updateSysUserExt(sysUserExt));
    }

    /**
     * 删除用户扩展信息
     */
    @PreAuthorize("@ss.hasPermi('system:ext:remove')")
    @Log(title = "用户扩展信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(sysUserExtService.deleteSysUserExtByIds(userIds));
    }
}
