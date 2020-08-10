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
import com.ruoyi.project.ci.domain.CompetitionComment;
import com.ruoyi.project.ci.service.ICompetitionCommentService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 竞赛评论Controller
 *
 * @author sun
 * @date 2020-08-09
 */
@RestController
@RequestMapping("/ci/compComment")
public class CompetitionCommentController extends BaseController {
    @Autowired
    private ICompetitionCommentService competitionCommentService;

    /**
     * 查询竞赛评论列表
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionComment competitionComment) {
        startPage();
        List<CompetitionComment> list = competitionCommentService.selectCompetitionCommentList(competitionComment);
        return getDataTable(list);
    }

    /**
     * 导出竞赛评论列表
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:export')")
    @Log(title = "竞赛评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CompetitionComment competitionComment) {
        List<CompetitionComment> list = competitionCommentService.selectCompetitionCommentList(competitionComment);
        ExcelUtil<CompetitionComment> util = new ExcelUtil<CompetitionComment>(CompetitionComment.class);
        return util.exportExcel(list, "compComment");
    }

    /**
     * 获取竞赛评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(competitionCommentService.selectCompetitionCommentById(id));
    }

    /**
     * 获取竞赛评论选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(CompetitionComment competitionComment) {
        return AjaxResult.success(competitionCommentService.selectCompetitionCommentOptions(competitionComment));
    }

    /**
     * 新增竞赛评论
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:add')")
    @Log(title = "竞赛评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionComment competitionComment) {
        return toAjax(competitionCommentService.insertCompetitionComment(competitionComment));
    }

    /**
     * 修改竞赛评论
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:edit')")
    @Log(title = "竞赛评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionComment competitionComment) {
        return toAjax(competitionCommentService.updateCompetitionComment(competitionComment));
    }

    /**
     * 删除竞赛评论
     */
    @PreAuthorize("@ss.hasPermi('ci:compComment:remove')")
    @Log(title = "竞赛评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(competitionCommentService.deleteCompetitionCommentByIds(ids));
    }
}
