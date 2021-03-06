package com.ruoyi.project.ci.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.ci.service.IUserTalentService;
import com.ruoyi.project.system.domain.SysUser;
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
import com.ruoyi.project.ci.domain.TeamExpect;
import com.ruoyi.project.ci.service.ITeamExpectService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 组队意愿Controller
 *
 * @author sun
 * @date 2020-04-21
 */
@RestController
@RequestMapping("/ci/expect")
public class TeamExpectController extends BaseController {
    @Autowired
    private ITeamExpectService teamExpectService;
    @Autowired
    private IUserTalentService userTalentService;

    /**
     * 查询组队意愿列表
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamExpect teamExpect) {
        startPage();
        listSelf(teamExpect);
        List<TeamExpect> list = teamExpectService.selectTeamExpectList(teamExpect);
        return getDataTable(list);
    }

    /**
     * 查询组队意愿列表
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:list')")
    @GetMapping("/list/news")
    public TableDataInfo listNew(TeamExpect teamExpect) {
        startPage();
        List<TeamExpect> list = teamExpectService.selectTeamExpectList(teamExpect);
        return getDataTable(list);
    }

    /**
     * 查询可能愿意加入队伍的用户 冷启动
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:list')")
    @GetMapping("/possible")
    public TableDataInfo listPossible(TeamExpect teamExpect) {
        startPage();
        // 真正的意愿数据
        List<TeamExpect> list = teamExpectService.selectTeamExpectList(teamExpect);
        // 如果意愿数据不足，则查询达人数据
        if (list == null || list.size() < 8) {
            List<SysUser> userList = userTalentService.selectUserTalentList();
            if(list == null) list = new ArrayList<>();
            for (SysUser user : userList) {
                TeamExpect temp = new TeamExpect();
                temp.setCapability("竞赛达人");
                temp.setCreateUser(user);
                list.add(temp);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出组队意愿列表
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:export')")
    @Log(title = "组队意愿", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamExpect teamExpect) {
        List<TeamExpect> list = teamExpectService.selectTeamExpectList(teamExpect);
        ExcelUtil<TeamExpect> util = new ExcelUtil<TeamExpect>(TeamExpect.class);
        return util.exportExcel(list, "expect");
    }

    /**
     * 获取组队意愿详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamExpectService.selectTeamExpectById(id));
    }

    /**
     * 获取组队意愿选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamExpect teamExpect) {
        return AjaxResult.success(teamExpectService.selectTeamExpectOptions(teamExpect));
    }

    /**
     * 新增组队意愿
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:add')")
    @Log(title = "组队意愿", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamExpect teamExpect) {
        return toAjax(teamExpectService.insertTeamExpect(teamExpect));
    }

    /**
     * 修改组队意愿
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:edit')")
    @Log(title = "组队意愿", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamExpect teamExpect) {
        return toAjax(teamExpectService.updateTeamExpect(teamExpect));
    }

    /**
     * 删除组队意愿
     */
    @PreAuthorize("@ss.hasPermi('ci:expect:remove')")
    @Log(title = "组队意愿", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamExpectService.deleteTeamExpectByIds(ids));
    }
}
