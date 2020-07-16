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
import com.ruoyi.project.ci.domain.RecommendCompetition;
import com.ruoyi.project.ci.service.IRecommendCompetitionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛推荐Controller
 *
 * @author sun
 * @date 2020-05-31
 */
@RestController
@RequestMapping("/ci/recoComp")
public class RecommendCompetitionController extends BaseController {
    @Autowired
    private IRecommendCompetitionService recommendCompetitionService;

    /**
     * 查询竞赛推荐列表
     */
//    @PreAuthorize("@ss.hasPermi('ci:recoComp:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecommendCompetition recommendCompetition) {
        startPage();
        List<RecommendCompetition> list = recommendCompetitionService.selectRecommendCompetitionList(recommendCompetition);
        return getDataTable(list);
    }

    /**
     * 导出竞赛推荐列表
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:export')")
    @Log(title = "竞赛推荐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RecommendCompetition recommendCompetition) {
        List<RecommendCompetition> list = recommendCompetitionService.selectRecommendCompetitionList(recommendCompetition);
        ExcelUtil<RecommendCompetition> util = new ExcelUtil<RecommendCompetition>(RecommendCompetition.class);
        return util.exportExcel(list, "recoComp");
    }

    /**
     * 获取竞赛推荐详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(recommendCompetitionService.selectRecommendCompetitionById(id));
    }

    /**
     * 获取竞赛推荐选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(RecommendCompetition recommendCompetition) {
        return AjaxResult.success(recommendCompetitionService.selectRecommendCompetitionOptions(recommendCompetition));
    }

    /**
     * 新增竞赛推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:add')")
    @Log(title = "竞赛推荐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecommendCompetition recommendCompetition) {
        return toAjax(recommendCompetitionService.insertRecommendCompetition(recommendCompetition));
    }

    /**
     * 修改竞赛推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:edit')")
    @Log(title = "竞赛推荐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecommendCompetition recommendCompetition) {
        return toAjax(recommendCompetitionService.updateRecommendCompetition(recommendCompetition));
    }

    /**
     * 删除竞赛推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:recoComp:remove')")
    @Log(title = "竞赛推荐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(recommendCompetitionService.deleteRecommendCompetitionByIds(ids));
    }
}
