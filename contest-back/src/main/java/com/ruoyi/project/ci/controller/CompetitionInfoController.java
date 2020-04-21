package com.ruoyi.project.ci.controller;

import java.util.List;
import java.util.Map;

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
import com.ruoyi.project.ci.domain.CompetitionInfo;
import com.ruoyi.project.ci.service.ICompetitionInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛信息模板Controller
 *
 * @author sun
 * @date 2020-04-17
 */
@RestController
@RequestMapping("/ci/info")
public class CompetitionInfoController extends BaseController {
    @Autowired
    private ICompetitionInfoService competitionInfoService;

    /**
     * 查询竞赛信息模板列表
     */
    @PreAuthorize("@ss.hasPermi('ci:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionInfo competitionInfo) {
        startPage();
        List<CompetitionInfo> list = competitionInfoService.selectCompetitionInfoList(competitionInfo);
        return getDataTable(list);
    }

    /**
     * 查询竞赛信息模板列表
     */
    @PreAuthorize("@ss.hasPermi('ci:info:options')")
    @GetMapping("/options")
    public AjaxResult getOptions(CompetitionInfo competitionInfo) {
        List<Map<String, Object>> options = competitionInfoService.selectCompetitionInfoOptions(competitionInfo);
        return AjaxResult.success(options);
    }

    /**
     * 导出竞赛信息模板列表
     */
    @PreAuthorize("@ss.hasPermi('ci:info:export')")
    @Log(title = "竞赛信息模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CompetitionInfo competitionInfo) {
        List<CompetitionInfo> list = competitionInfoService.selectCompetitionInfoList(competitionInfo);
        ExcelUtil<CompetitionInfo> util = new ExcelUtil<CompetitionInfo>(CompetitionInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取竞赛信息模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionInfoService.selectCompetitionInfoById(id));
    }

    /**
     * 新增竞赛信息模板
     */
    @PreAuthorize("@ss.hasPermi('ci:info:add')")
    @Log(title = "竞赛信息模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionInfo competitionInfo) {
        return toAjax(competitionInfoService.insertCompetitionInfo(competitionInfo));
    }

    /**
     * 修改竞赛信息模板
     */
    @PreAuthorize("@ss.hasPermi('ci:info:edit')")
    @Log(title = "竞赛信息模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionInfo competitionInfo) {
        return toAjax(competitionInfoService.updateCompetitionInfo(competitionInfo));
    }

    /**
     * 删除竞赛信息模板
     */
    @PreAuthorize("@ss.hasPermi('ci:info:remove')")
    @Log(title = "竞赛信息模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(competitionInfoService.deleteCompetitionInfoByIds(ids));
    }
}
