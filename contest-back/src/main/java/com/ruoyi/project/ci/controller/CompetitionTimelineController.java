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
import com.ruoyi.project.ci.domain.CompetitionTimeline;
import com.ruoyi.project.ci.service.ICompetitionTimelineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛资讯Controller
 *
 * @author sun
 * @date 2020-08-10
 */
@RestController
@RequestMapping("/ci/timeline")
public class CompetitionTimelineController extends BaseController {
    @Autowired
    private ICompetitionTimelineService competitionTimelineService;

    /**
     * 查询竞赛资讯列表
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionTimeline competitionTimeline) {
        startPage();
        List<CompetitionTimeline> list = competitionTimelineService.selectCompetitionTimelineList(competitionTimeline);
        return getDataTable(list);
    }

    /**
     * 导出竞赛资讯列表
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:export')")
    @Log(title = "竞赛资讯", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CompetitionTimeline competitionTimeline) {
        List<CompetitionTimeline> list = competitionTimelineService.selectCompetitionTimelineList(competitionTimeline);
        ExcelUtil<CompetitionTimeline> util = new ExcelUtil<CompetitionTimeline>(CompetitionTimeline.class);
        return util.exportExcel(list, "timeline");
    }

    /**
     * 获取竞赛资讯详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionTimelineService.selectCompetitionTimelineById(id));
    }

    /**
     * 获取竞赛资讯选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(CompetitionTimeline competitionTimeline) {
        return AjaxResult.success(competitionTimelineService.selectCompetitionTimelineOptions(competitionTimeline));
    }

    /**
     * 新增竞赛资讯
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:add')")
    @Log(title = "竞赛资讯", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionTimeline competitionTimeline) {
        return toAjax(competitionTimelineService.insertCompetitionTimeline(competitionTimeline));
    }

    /**
     * 修改竞赛资讯
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:edit')")
    @Log(title = "竞赛资讯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionTimeline competitionTimeline) {
        return toAjax(competitionTimelineService.updateCompetitionTimeline(competitionTimeline));
    }

    /**
     * 删除竞赛资讯
     */
    @PreAuthorize("@ss.hasPermi('ci:timeline:remove')")
    @Log(title = "竞赛资讯", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(competitionTimelineService.deleteCompetitionTimelineByIds(ids));
    }
}
