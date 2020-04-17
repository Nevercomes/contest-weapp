package com.ruoyi.project.system.controller;

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
import com.ruoyi.project.system.domain.EduSchool;
import com.ruoyi.project.system.service.IEduSchoolService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 学校信息Controller
 *
 * @author sun
 * @date 2020-04-17
 */
@RestController
@RequestMapping("/system/school")
public class EduSchoolController extends BaseController {
    @Autowired
    private IEduSchoolService eduSchoolService;

    /**
     * 查询学校信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduSchool eduSchool) {
        startPage();
        List<EduSchool> list = eduSchoolService.selectEduSchoolList(eduSchool);
        return getDataTable(list);
    }

    /**
     * 导出学校信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:export')")
    @Log(title = "学校信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduSchool eduSchool) {
        List<EduSchool> list = eduSchoolService.selectEduSchoolList(eduSchool);
        ExcelUtil<EduSchool> util = new ExcelUtil<EduSchool>(EduSchool.class);
        return util.exportExcel(list, "school");
    }

    /**
     * 获取学校信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:query')")
    @GetMapping(value = "/{schoolId}")
    public AjaxResult getInfo(@PathVariable("schoolId") Long schoolId) {
        return AjaxResult.success(eduSchoolService.selectEduSchoolById(schoolId));
    }

    /**
     * 新增学校信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:add')")
    @Log(title = "学校信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduSchool eduSchool) {
        return toAjax(eduSchoolService.insertEduSchool(eduSchool));
    }

    /**
     * 修改学校信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:edit')")
    @Log(title = "学校信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduSchool eduSchool) {
        return toAjax(eduSchoolService.updateEduSchool(eduSchool));
    }

    /**
     * 删除学校信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:remove')")
    @Log(title = "学校信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{schoolIds}")
    public AjaxResult remove(@PathVariable Long[] schoolIds) {
        return toAjax(eduSchoolService.deleteEduSchoolByIds(schoolIds));
    }
}
