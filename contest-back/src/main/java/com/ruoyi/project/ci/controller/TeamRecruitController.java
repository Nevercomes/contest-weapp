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
import com.ruoyi.project.ci.domain.TeamRecruit;
import com.ruoyi.project.ci.service.ITeamRecruitService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 队员招募Controller
 *
 * @author sun
 * @date 2020-04-21
 */
@RestController
@RequestMapping("/ci/recruit")
public class TeamRecruitController extends BaseController {
    @Autowired
    private ITeamRecruitService teamRecruitService;

    /**
     * 查询队员招募列表
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamRecruit teamRecruit) {
        startPage();
        List<TeamRecruit> list = teamRecruitService.selectTeamRecruitList(teamRecruit);
        return getDataTable(list);
    }

    /**
     * 导出队员招募列表
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:export')")
    @Log(title = "队员招募", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamRecruit teamRecruit) {
        List<TeamRecruit> list = teamRecruitService.selectTeamRecruitList(teamRecruit);
        ExcelUtil<TeamRecruit> util = new ExcelUtil<TeamRecruit>(TeamRecruit.class);
        return util.exportExcel(list, "recruit");
    }

    /**
     * 获取队员招募详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamRecruitService.selectTeamRecruitById(id));
    }

    /**
     * 获取队员招募选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamRecruit teamRecruit) {
        return AjaxResult.success(teamRecruitService.selectTeamRecruitOptions(teamRecruit));
    }

    /**
     * 新增队员招募
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:add')")
    @Log(title = "队员招募", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamRecruit teamRecruit) {
        return toAjax(teamRecruitService.insertTeamRecruit(teamRecruit));
    }

    /**
     * 修改队员招募
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:edit')")
    @Log(title = "队员招募", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamRecruit teamRecruit) {
        return toAjax(teamRecruitService.updateTeamRecruit(teamRecruit));
    }

    /**
     * 删除队员招募
     */
    @PreAuthorize("@ss.hasPermi('ci:recruit:remove')")
    @Log(title = "队员招募", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamRecruitService.deleteTeamRecruitByIds(ids));
    }
}
