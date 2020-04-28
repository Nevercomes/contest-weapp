package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.project.system.domain.EduSchool;
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
import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.service.ICompetitionPeriodService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛列表信息Controller
 *
 * @author sun
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/ci/period")
public class CompetitionPeriodController extends BaseController {
    @Autowired
    private ICompetitionPeriodService competitionPeriodService;

    /**
     * 查询竞赛列表信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:period:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionPeriod competitionPeriod) {
        startPage();
        List<CompetitionPeriod> list = competitionPeriodService.selectCompetitionPeriodList(competitionPeriod);
        return getDataTable(list);
    }

    /**
     * 导出竞赛列表信息列表
     */
    @PreAuthorize("@ss.hasPermi('ci:period:export')")
    @Log(title = "竞赛列表信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CompetitionPeriod competitionPeriod) {
        List<CompetitionPeriod> list = competitionPeriodService.selectCompetitionPeriodList(competitionPeriod);
        ExcelUtil<CompetitionPeriod> util = new ExcelUtil<CompetitionPeriod>(CompetitionPeriod.class);
        return util.exportExcel(list, "period");
    }

    /**
     * 获取竞赛列表信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:period:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionPeriodService.selectCompetitionPeriodById(id));
    }

    /**
     * 获取竞赛列表选项
     */
    @PreAuthorize("@ss.hasPermi('system:period:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(CompetitionPeriod competitionPeriod) {
        return AjaxResult.success(competitionPeriodService.selectCompetitionPeriodOptions(competitionPeriod));
    }

    /**
     * 新增竞赛列表信息
     */
    @PreAuthorize("@ss.hasPermi('ci:period:add')")
    @Log(title = "竞赛列表信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionPeriod competitionPeriod) {
        return toAjax(competitionPeriodService.insertCompetitionPeriod(competitionPeriod));
    }

    /**
     * 修改竞赛列表信息
     */
    @PreAuthorize("@ss.hasPermi('ci:period:edit')")
    @Log(title = "竞赛列表信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionPeriod competitionPeriod) {
        return toAjax(competitionPeriodService.updateCompetitionPeriod(competitionPeriod));
    }

    /**
     * 删除竞赛列表信息
     */
    @PreAuthorize("@ss.hasPermi('ci:period:remove')")
    @Log(title = "竞赛列表信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(competitionPeriodService.deleteCompetitionPeriodByIds(ids));
    }
}