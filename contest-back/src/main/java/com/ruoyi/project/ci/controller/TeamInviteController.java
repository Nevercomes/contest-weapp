package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.ci.domain.TeamApply;
import com.ruoyi.project.ci.domain.TeamInfo;
import com.ruoyi.project.ci.domain.TeamMember;
import com.ruoyi.project.ci.service.ITeamMemberService;
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
import com.ruoyi.project.ci.domain.TeamInvite;
import com.ruoyi.project.ci.service.ITeamInviteService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 入队邀请Controller
 *
 * @author sun
 * @date 2020-04-24
 */
@RestController
@RequestMapping("/ci/invite")
public class TeamInviteController extends BaseController {
    @Autowired
    private ITeamInviteService teamInviteService;
    @Autowired
    private ITeamMemberService teamMemberService;

    /**
     * 查询入队邀请列表
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamInvite teamInvite) {
        startPage();
        List<TeamInvite> list = teamInviteService.selectTeamInviteList(teamInvite);
        return getDataTable(list);
    }

    /**
     * 查询自己发送的入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:list')")
    @GetMapping("/list/send")
    public TableDataInfo listSend(TeamInvite teamInvite) {
        startPage();
        listSelf(teamInvite);
        List<TeamInvite> list = teamInviteService.selectTeamInviteList(teamInvite);
        return getDataTable(list);
    }

    /**
     * 查询队伍收到的入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:list')")
    @GetMapping("/list/receive")
    public TableDataInfo listReceive(TeamInvite teamInvite) {
        startPage();
        teamInvite.setInvitedUserId(SecurityUtils.getUserId());
        List<TeamInvite> list = teamInviteService.selectTeamInviteList(teamInvite);
        return getDataTable(list);
    }

    /**
     * 导出入队邀请列表
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:export')")
    @Log(title = "入队邀请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamInvite teamInvite) {
        List<TeamInvite> list = teamInviteService.selectTeamInviteList(teamInvite);
        ExcelUtil<TeamInvite> util = new ExcelUtil<TeamInvite>(TeamInvite.class);
        return util.exportExcel(list, "invite");
    }

    /**
     * 获取入队邀请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamInviteService.selectTeamInviteById(id));
    }

    /**
     * 获取入队邀请选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamInvite teamInvite) {
        return AjaxResult.success(teamInviteService.selectTeamInviteOptions(teamInvite));
    }

    /**
     * 新增入队邀请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:add')")
    @Log(title = "入队邀请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamInvite teamInvite) {
        return toAjax(teamInviteService.insertTeamInvite(teamInvite));
    }

    /**
     * 修改入队邀请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:edit')")
    @Log(title = "入队邀请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamInvite teamInvite) {
        return toAjax(teamInviteService.updateTeamInvite(teamInvite));
    }

    /**
     * 通过入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:edit')")
    @Log(title = "入队邀请", businessType = BusinessType.UPDATE)
    @PutMapping("/pass/{id}")
    public AjaxResult pass(@PathVariable("id") Long id) {
        TeamInvite invite = teamInviteService.selectTeamInviteById(id);
        invite.setStatus("1");
        teamInviteService.updateTeamInvite(invite);
        // 添加队员
        TeamMember member = new TeamMember();
        member.setTeamId(invite.getTeamId());
        member.setUserId(invite.getInvitedUserId());
        member.setIdentity("2");
        int res = teamMemberService.insertTeamMember(member);
        return res > 0 ? AjaxResult.success() : AjaxResult.error("该用户已是队员");
    }

    /**
     * 拒绝入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:edit')")
    @Log(title = "入队邀请", businessType = BusinessType.UPDATE)
    @PutMapping("/reject/{id}")
    public AjaxResult reject(@PathVariable("id") Long id) {
        TeamInvite invite = teamInviteService.selectTeamInviteById(id);
        invite.setStatus("2");
        int res = teamInviteService.updateTeamInvite(invite);
        // 产生拒绝通知
        return toAjax(res);
    }

    /**
     * 删除入队邀请
     */
    @PreAuthorize("@ss.hasPermi('ci:invite:remove')")
    @Log(title = "入队邀请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamInviteService.deleteTeamInviteByIds(ids));
    }
}
