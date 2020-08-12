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
import com.ruoyi.project.ci.domain.TeamDefaultAvatar;
import com.ruoyi.project.ci.service.ITeamDefaultAvatarService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 默认团队头像Controller
 *
 * @author sun
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/ci/teamDfAvatar")
public class TeamDefaultAvatarController extends BaseController {
    @Autowired
    private ITeamDefaultAvatarService teamDefaultAvatarService;

    /**
     * 查询默认团队头像列表
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamDefaultAvatar teamDefaultAvatar) {
        startPage();
        List<TeamDefaultAvatar> list = teamDefaultAvatarService.selectTeamDefaultAvatarList(teamDefaultAvatar);
        return getDataTable(list);
    }

    /**
     * 导出默认团队头像列表
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:export')")
    @Log(title = "默认团队头像", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamDefaultAvatar teamDefaultAvatar) {
        List<TeamDefaultAvatar> list = teamDefaultAvatarService.selectTeamDefaultAvatarList(teamDefaultAvatar);
        ExcelUtil<TeamDefaultAvatar> util = new ExcelUtil<TeamDefaultAvatar>(TeamDefaultAvatar.class);
        return util.exportExcel(list, "teamAvatar");
    }

    /**
     * 获取默认团队头像详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamDefaultAvatarService.selectTeamDefaultAvatarById(id));
    }

    /**
     * 获取默认团队头像选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamDefaultAvatar teamDefaultAvatar) {
        return AjaxResult.success(teamDefaultAvatarService.selectTeamDefaultAvatarOptions(teamDefaultAvatar));
    }

    /**
     * 新增默认团队头像
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:add')")
    @Log(title = "默认团队头像", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamDefaultAvatar teamDefaultAvatar) {
        return toAjax(teamDefaultAvatarService.insertTeamDefaultAvatar(teamDefaultAvatar));
    }

    /**
     * 修改默认团队头像
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:edit')")
    @Log(title = "默认团队头像", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamDefaultAvatar teamDefaultAvatar) {
        return toAjax(teamDefaultAvatarService.updateTeamDefaultAvatar(teamDefaultAvatar));
    }

    /**
     * 删除默认团队头像
     */
    @PreAuthorize("@ss.hasPermi('ci:teamDfAvatar:remove')")
    @Log(title = "默认团队头像", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamDefaultAvatarService.deleteTeamDefaultAvatarByIds(ids));
    }
}
