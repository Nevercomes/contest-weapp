package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.ci.domain.UserExperience;
import com.ruoyi.project.ci.service.IUserExperienceService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛经历Controller
 *
 * @author sun
 * @date 2020-04-25
 */
@RestController
@RequestMapping("/ci/experience")
public class UserExperienceController extends BaseController {
    @Autowired
    private IUserExperienceService userExperienceService;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询竞赛经历列表
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:list')")
    @GetMapping(value = {"/list", "/list/{userId}"})
    public TableDataInfo list(UserExperience userExperience, @PathVariable(value = "userId", required = false) Long userId) {
        if (userId != null) {
            SysUser user = userService.selectUserById(userId);
            userExperience.setCreateBy(user.getUserName());
        } else {
            listSelf(userExperience);
        }
        startPage();
        List<UserExperience> list = userExperienceService.selectUserExperienceList(userExperience);
        return getDataTable(list);
    }

    /**
     * 导出竞赛经历列表
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:export')")
    @Log(title = "竞赛经历", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserExperience userExperience) {
        List<UserExperience> list = userExperienceService.selectUserExperienceList(userExperience);
        ExcelUtil<UserExperience> util = new ExcelUtil<UserExperience>(UserExperience.class);
        return util.exportExcel(list, "experience");
    }

    /**
     * 获取竞赛经历详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userExperienceService.selectUserExperienceById(id));
    }

    /**
     * 获取竞赛经历选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(UserExperience userExperience) {
        return AjaxResult.success(userExperienceService.selectUserExperienceOptions(userExperience));
    }

    /**
     * 新增竞赛经历
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:add')")
    @Log(title = "竞赛经历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserExperience userExperience) {
        return toAjax(userExperienceService.insertUserExperience(userExperience));
    }

    /**
     * 修改竞赛经历
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:edit')")
    @Log(title = "竞赛经历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserExperience userExperience) {
        return toAjax(userExperienceService.updateUserExperience(userExperience));
    }

    /**
     * 删除竞赛经历
     */
    @PreAuthorize("@ss.hasPermi('ci:experience:remove')")
    @Log(title = "竞赛经历", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userExperienceService.deleteUserExperienceByIds(ids));
    }
}
