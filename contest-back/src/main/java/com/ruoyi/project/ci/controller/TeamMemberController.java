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
import com.ruoyi.project.ci.domain.TeamMember;
import com.ruoyi.project.ci.service.ITeamMemberService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 队伍成员Controller
 *
 * @author sun
 * @date 2020-04-21
 */
@RestController
@RequestMapping("/ci/member")
public class TeamMemberController extends BaseController {
    @Autowired
    private ITeamMemberService teamMemberService;

    /**
     * 查询队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('ci:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamMember teamMember) {
        startPage();
        List<TeamMember> list = teamMemberService.selectTeamMemberList(teamMember);
        return getDataTable(list);
    }

    /**
     * 导出队伍成员列表
     */
    @PreAuthorize("@ss.hasPermi('ci:member:export')")
    @Log(title = "队伍成员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamMember teamMember) {
        List<TeamMember> list = teamMemberService.selectTeamMemberList(teamMember);
        ExcelUtil<TeamMember> util = new ExcelUtil<TeamMember>(TeamMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 获取队伍成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamMemberService.selectTeamMemberById(id));
    }

    /**
     * 获取队伍成员选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:member:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamMember teamMember) {
        return AjaxResult.success(teamMemberService.selectTeamMemberOptions(teamMember));
    }

    /**
     * 新增队伍成员
     */
    @PreAuthorize("@ss.hasPermi('ci:member:add')")
    @Log(title = "队伍成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamMember teamMember) {
        return toAjax(teamMemberService.insertTeamMember(teamMember));
    }

    /**
     * 修改队伍成员
     */
    @PreAuthorize("@ss.hasPermi('ci:member:edit')")
    @Log(title = "队伍成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamMember teamMember) {
        return toAjax(teamMemberService.updateTeamMember(teamMember));
    }

    /**
     * 删除队伍成员
     */
    @PreAuthorize("@ss.hasPermi('ci:member:remove')")
    @Log(title = "队伍成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamMemberService.deleteTeamMemberByIds(ids));
    }
}
