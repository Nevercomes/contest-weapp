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
import com.ruoyi.project.ci.domain.TeamInfo;
import com.ruoyi.project.ci.service.ITeamInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 队伍信息Controller
 *
 * @author sun
 * @date 2020-04-21
 */
@RestController
@RequestMapping("/ci/teamInfo")
public class TeamInfoController extends BaseController {
    @Autowired
    private ITeamInfoService teamInfoService;

    /**
     * 查询队伍信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamInfo teamInfo) {
        startPage();
        List<TeamInfo> list = teamInfoService.selectTeamInfoList(teamInfo);
        return getDataTable(list);
    }

    /**
     * 导出队伍信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:export')")
    @Log(title = "队伍信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamInfo teamInfo) {
        List<TeamInfo> list = teamInfoService.selectTeamInfoList(teamInfo);
        ExcelUtil<TeamInfo> util = new ExcelUtil<TeamInfo>(TeamInfo.class);
        return util.exportExcel(list, "teamInfo");
    }

    /**
     * 获取队伍信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamInfoService.selectTeamInfoById(id));
    }

    /**
     * 获取队伍信息选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamInfo teamInfo) {
        return AjaxResult.success(teamInfoService.selectTeamInfoOptions(teamInfo));
    }

    /**
     * 新增队伍信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:add')")
    @Log(title = "队伍信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamInfo teamInfo) {
        return AjaxResult.success(teamInfoService.insertTeamInfo(teamInfo));
    }

    /**
     * 修改队伍信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:edit')")
    @Log(title = "队伍信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamInfo teamInfo) {
        return toAjax(teamInfoService.updateTeamInfo(teamInfo));
    }

    /**
     * 删除队伍信息
     */
    @PreAuthorize("@ss.hasPermi('ci:teamInfo:remove')")
    @Log(title = "队伍信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamInfoService.deleteTeamInfoByIds(ids));
    }
}
