package com.ruoyi.project.ci.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
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
import com.ruoyi.project.ci.domain.PointsAction;
import com.ruoyi.project.ci.service.IPointsActionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 积分记录Controller
 *
 * @author sun
 * @date 2020-04-26
 */
@RestController
@RequestMapping("/ci/points")
public class PointsActionController extends BaseController {
    @Autowired
    private IPointsActionService pointsActionService;

    /**
     * 查询积分记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:points:list')")
    @GetMapping("/list")
    @DataScope(userAlias = "u")
    public TableDataInfo list(PointsAction pointsAction) {
        startPage();
        List<PointsAction> list = pointsActionService.selectPointsActionList(pointsAction);
        return getDataTable(list);
    }

    /**
     * 导出积分记录列表
     */
    @PreAuthorize("@ss.hasPermi('ci:points:export')")
    @Log(title = "积分记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PointsAction pointsAction) {
        List<PointsAction> list = pointsActionService.selectPointsActionList(pointsAction);
        ExcelUtil<PointsAction> util = new ExcelUtil<PointsAction>(PointsAction.class);
        return util.exportExcel(list, "points");
    }

    /**
     * 获取积分记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:points:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(pointsActionService.selectPointsActionById(id));
    }

    /**
     * 查询用户积分
     */
    @PreAuthorize("@ss.hasPermi('ci:points:query')")
    @GetMapping(value = "/user")
    public AjaxResult getUserPoints() {
        Long userId = SecurityUtils.getUserId();
        return AjaxResult.success(pointsActionService.getUserPoints(userId));
    }

    /**
     * 获取积分记录选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:points:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(PointsAction pointsAction) {
        return AjaxResult.success(pointsActionService.selectPointsActionOptions(pointsAction));
    }

    /**
     * 新增积分记录
     */
    @PreAuthorize("@ss.hasPermi('ci:points:add')")
    @Log(title = "积分记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsAction pointsAction) {
        return toAjax(pointsActionService.insertPointsAction(pointsAction));
    }

    /**
     * 修改积分记录
     */
    @PreAuthorize("@ss.hasPermi('ci:points:edit')")
    @Log(title = "积分记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsAction pointsAction) {
        return toAjax(pointsActionService.updatePointsAction(pointsAction));
    }

    /**
     * 删除积分记录
     */
    @PreAuthorize("@ss.hasPermi('ci:points:remove')")
    @Log(title = "积分记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(pointsActionService.deletePointsActionByIds(ids));
    }
}
