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
import com.ruoyi.project.ci.domain.TeamApply;
import com.ruoyi.project.ci.service.ITeamApplyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 入队申请Controller
 *
 * @author sun
 * @date 2020-04-24
 */
@RestController
@RequestMapping("/ci/apply")
public class TeamApplyController extends BaseController {
    @Autowired
    private ITeamApplyService teamApplyService;

    /**
     * 查询入队申请列表
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamApply teamApply) {
        startPage();
        List<TeamApply> list = teamApplyService.selectTeamApplyList(teamApply);
        return getDataTable(list);
    }

    /**
     * 导出入队申请列表
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:export')")
    @Log(title = "入队申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TeamApply teamApply) {
        List<TeamApply> list = teamApplyService.selectTeamApplyList(teamApply);
        ExcelUtil<TeamApply> util = new ExcelUtil<TeamApply>(TeamApply.class);
        return util.exportExcel(list, "apply");
    }

    /**
     * 获取入队申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(teamApplyService.selectTeamApplyById(id));
    }

    /**
     * 获取入队申请选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(TeamApply teamApply) {
        return AjaxResult.success(teamApplyService.selectTeamApplyOptions(teamApply));
    }

    /**
     * 新增入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:add')")
    @Log(title = "入队申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamApply teamApply) {
        return toAjax(teamApplyService.insertTeamApply(teamApply));
    }

    /**
     * 修改入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:edit')")
    @Log(title = "入队申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamApply teamApply) {
        return toAjax(teamApplyService.updateTeamApply(teamApply));
    }

    /**
     * 删除入队申请
     */
    @PreAuthorize("@ss.hasPermi('ci:apply:remove')")
    @Log(title = "入队申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(teamApplyService.deleteTeamApplyByIds(ids));
    }
}
