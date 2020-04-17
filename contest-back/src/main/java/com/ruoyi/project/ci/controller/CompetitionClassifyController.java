package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.domain.SysMenu;
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
import com.ruoyi.project.ci.domain.CompetitionClassify;
import com.ruoyi.project.ci.service.ICompetitionClassifyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 竞赛分类Controller
 *
 * @author sun
 * @date 2020-04-17
 */
@RestController
@RequestMapping("/ci/classify")
public class CompetitionClassifyController extends BaseController {
    @Autowired
    private ICompetitionClassifyService competitionClassifyService;

    /**
     * 查询竞赛分类列表
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:list')")
    @GetMapping("/list")
    public AjaxResult list(CompetitionClassify competitionClassify) {
        List<CompetitionClassify> list = competitionClassifyService.selectCompetitionClassifyList(competitionClassify);
        return AjaxResult.success(list);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(CompetitionClassify competitionClassify) {
        List<CompetitionClassify> list = competitionClassifyService.selectCompetitionClassifyList(competitionClassify);
        return AjaxResult.success(competitionClassifyService.buildCompetitionClassifyTreeSelect(list));
    }

    /**
     * 加载对应竞赛分类列表树
     */
    @GetMapping(value = "/infoClassifyTreeselect/{infoId}")
    public AjaxResult infoClassifyTreeselect(@PathVariable("infoId") Long infoId) {
        List<CompetitionClassify> classifies = competitionClassifyService.selectCompetitionClassifyList(new CompetitionClassify());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", competitionClassifyService.selectCompetitionClassifyListByInfoId(infoId));
        ajax.put("classifies", competitionClassifyService.buildCompetitionClassifyTreeSelect(classifies));
        return ajax;
    }

    /**
     * 导出竞赛分类列表
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:export')")
    @Log(title = "竞赛分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CompetitionClassify competitionClassify) {
        List<CompetitionClassify> list = competitionClassifyService.selectCompetitionClassifyList(competitionClassify);
        ExcelUtil<CompetitionClassify> util = new ExcelUtil<CompetitionClassify>(CompetitionClassify.class);
        return util.exportExcel(list, "classify");
    }

    /**
     * 获取竞赛分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionClassifyService.selectCompetitionClassifyById(id));
    }

    /**
     * 新增竞赛分类
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:add')")
    @Log(title = "竞赛分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionClassify competitionClassify) {
        return toAjax(competitionClassifyService.insertCompetitionClassify(competitionClassify));
    }

    /**
     * 修改竞赛分类
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:edit')")
    @Log(title = "竞赛分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionClassify competitionClassify) {
        return toAjax(competitionClassifyService.updateCompetitionClassify(competitionClassify));
    }

    /**
     * 删除竞赛分类
     */
    @PreAuthorize("@ss.hasPermi('ci:classify:remove')")
    @Log(title = "竞赛分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(competitionClassifyService.deleteCompetitionClassifyByIds(ids));
    }
}
