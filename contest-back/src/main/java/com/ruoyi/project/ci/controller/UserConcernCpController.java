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
import com.ruoyi.project.ci.domain.UserConcernCp;
import com.ruoyi.project.ci.service.IUserConcernCpService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 关注竞赛Controller
 *
 * @author sun
 * @date 2020-04-25
 */
@RestController
@RequestMapping("/ci/concernCp")
public class UserConcernCpController extends BaseController {
    @Autowired
    private IUserConcernCpService userConcernCpService;

    /**
     * 查询关注竞赛列表
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserConcernCp userConcernCp) {
        startPage();
        List<UserConcernCp> list = userConcernCpService.selectUserConcernCpList(userConcernCp);
        return getDataTable(list);
    }

    /**
     * 导出关注竞赛列表
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:export')")
    @Log(title = "关注竞赛", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserConcernCp userConcernCp) {
        List<UserConcernCp> list = userConcernCpService.selectUserConcernCpList(userConcernCp);
        ExcelUtil<UserConcernCp> util = new ExcelUtil<UserConcernCp>(UserConcernCp.class);
        return util.exportExcel(list, "concernCp");
    }

    /**
     * 获取关注竞赛详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userConcernCpService.selectUserConcernCpById(id));
    }

    /**
     * 获取关注竞赛选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(UserConcernCp userConcernCp) {
        return AjaxResult.success(userConcernCpService.selectUserConcernCpOptions(userConcernCp));
    }

    /**
     * 新增关注竞赛
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:add')")
    @Log(title = "关注竞赛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserConcernCp userConcernCp) {
        return toAjax(userConcernCpService.insertUserConcernCp(userConcernCp));
    }

    /**
     * 修改关注竞赛
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:edit')")
    @Log(title = "关注竞赛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserConcernCp userConcernCp) {
        return toAjax(userConcernCpService.updateUserConcernCp(userConcernCp));
    }

    /**
     * 删除关注竞赛
     */
    @PreAuthorize("@ss.hasPermi('ci:concernCp:remove')")
    @Log(title = "关注竞赛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userConcernCpService.deleteUserConcernCpByIds(ids));
    }
}
