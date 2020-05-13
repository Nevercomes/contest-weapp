package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.project.ci.domain.UserShowSetting;
import com.ruoyi.project.ci.service.IUserShowSettingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户显示设置Controller
 *
 * @author sun
 * @date 2020-05-13
 */
@RestController
@RequestMapping("/ci/showSetting")
public class UserShowSettingController extends BaseController {
    @Autowired
    private IUserShowSettingService userShowSettingService;

    /**
     * 查询用户显示设置列表
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserShowSetting userShowSetting) {
        startPage();
        List<UserShowSetting> list = userShowSettingService.selectUserShowSettingList(userShowSetting);
        return getDataTable(list);
    }

    /**
     * 导出用户显示设置列表
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:export')")
    @Log(title = "用户显示设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserShowSetting userShowSetting) {
        List<UserShowSetting> list = userShowSettingService.selectUserShowSettingList(userShowSetting);
        ExcelUtil<UserShowSetting> util = new ExcelUtil<UserShowSetting>(UserShowSetting.class);
        return util.exportExcel(list, "showSetting");
    }

    /**
     * 获取用户显示设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:query')")
    @GetMapping(value = {"/{ownUserId}", ""})
    public AjaxResult getInfo(@PathVariable(value = "ownUserId", required = false) Long ownUserId) {
        Long userId = ownUserId != null ? ownUserId : SecurityUtils.getUserId();
        return AjaxResult.success(userShowSettingService.selectUserShowSettingById(userId));
    }

    /**
     * 获取用户显示设置选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(UserShowSetting userShowSetting) {
        return AjaxResult.success(userShowSettingService.selectUserShowSettingOptions(userShowSetting));
    }

    /**
     * 新增用户显示设置
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:add')")
    @Log(title = "用户显示设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserShowSetting userShowSetting) {
        return toAjax(userShowSettingService.insertUserShowSetting(userShowSetting));
    }

    /**
     * 修改用户显示设置
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:edit')")
    @Log(title = "用户显示设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserShowSetting userShowSetting) {
        return toAjax(userShowSettingService.updateUserShowSetting(userShowSetting));
    }

    /**
     * 删除用户显示设置
     */
    @PreAuthorize("@ss.hasPermi('ci:showSetting:remove')")
    @Log(title = "用户显示设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ownUserIds}")
    public AjaxResult remove(@PathVariable Long[] ownUserIds) {
        return toAjax(userShowSettingService.deleteUserShowSettingByIds(ownUserIds));
    }
}
