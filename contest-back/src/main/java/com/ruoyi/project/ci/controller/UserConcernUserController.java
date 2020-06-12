package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.ci.domain.UserConcernUser;
import com.ruoyi.project.ci.service.IUserConcernUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 关注用户Controller
 *
 * @author sun
 * @date 2020-04-25
 */
@RestController
@RequestMapping("/ci/concernUser")
public class UserConcernUserController extends BaseController {
    @Autowired
    private IUserConcernUserService userConcernUserService;

    /**
     * 查询关注用户列表
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserConcernUser userConcernUser) {
        startPage();
        listSelf((userConcernUser));
        List<UserConcernUser> list = userConcernUserService.selectUserConcernUserList(userConcernUser);
        return getDataTable(list);
    }

    /**
     * 导出关注用户列表
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:export')")
    @Log(title = "关注用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserConcernUser userConcernUser) {
        List<UserConcernUser> list = userConcernUserService.selectUserConcernUserList(userConcernUser);
        ExcelUtil<UserConcernUser> util = new ExcelUtil<UserConcernUser>(UserConcernUser.class);
        return util.exportExcel(list, "concernUser");
    }

    /**
     * 获取关注用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userConcernUserService.selectUserConcernUserById(id));
    }

    /**
     * 获取关注用户选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(UserConcernUser userConcernUser) {
        return AjaxResult.success(userConcernUserService.selectUserConcernUserOptions(userConcernUser));
    }

    /**
     * 新增关注用户
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:add')")
    @Log(title = "关注用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserConcernUser userConcernUser) {
        UserConcernUser res = userConcernUserService.insertUserConcernUser(userConcernUser);
        return AjaxResult.success(res);
    }

    /**
     * 修改关注用户
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:edit')")
    @Log(title = "关注用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserConcernUser userConcernUser) {
        return toAjax(userConcernUserService.updateUserConcernUser(userConcernUser));
    }

    /**
     * 删除关注用户
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:remove')")
    @Log(title = "关注用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userConcernUserService.deleteUserConcernUserByIds(ids));
    }

    /**
     * 删除关注用户
     */
    @PreAuthorize("@ss.hasPermi('ci:concernUser:remove')")
    @Log(title = "关注用户", businessType = BusinessType.DELETE)
    @DeleteMapping()
    public AjaxResult removeByUserId(@RequestParam("userId") Long userId)  {
        UserConcernUser query = new UserConcernUser();
        query.setUserId(userId);
        query.setCreateBy(SecurityUtils.getUsername());
        List<UserConcernUser> list = userConcernUserService.selectUserConcernUserList(query);
        return toAjax(userConcernUserService.deleteUserConcernUserByIds(list.stream().map(UserConcernUser::getId).toArray(Long[]::new)));
    }

}
