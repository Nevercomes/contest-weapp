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
import com.ruoyi.project.ci.domain.RecommendSwiper;
import com.ruoyi.project.ci.service.IRecommendSwiperService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 首页推荐Controller
 *
 * @author sun
 * @date 2020-05-31
 */
@RestController
@RequestMapping("/ci/swiper")
public class RecommendSwiperController extends BaseController {
    @Autowired
    private IRecommendSwiperService recommendSwiperService;

    /**
     * 查询首页推荐列表
     */
//    @PreAuthorize("@ss.hasPermi('ci:swiper:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecommendSwiper recommendSwiper) {
        startPage();
        List<RecommendSwiper> list = recommendSwiperService.selectRecommendSwiperList(recommendSwiper);
        return getDataTable(list);
    }

    /**
     * 导出首页推荐列表
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:export')")
    @Log(title = "首页推荐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RecommendSwiper recommendSwiper) {
        List<RecommendSwiper> list = recommendSwiperService.selectRecommendSwiperList(recommendSwiper);
        ExcelUtil<RecommendSwiper> util = new ExcelUtil<RecommendSwiper>(RecommendSwiper.class);
        return util.exportExcel(list, "swiper");
    }

    /**
     * 获取首页推荐详细信息
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(recommendSwiperService.selectRecommendSwiperById(id));
    }

    /**
     * 获取首页推荐选项信息
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:options')")
    @GetMapping(value = "/options")
    public AjaxResult options(RecommendSwiper recommendSwiper) {
        return AjaxResult.success(recommendSwiperService.selectRecommendSwiperOptions(recommendSwiper));
    }

    /**
     * 新增首页推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:add')")
    @Log(title = "首页推荐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecommendSwiper recommendSwiper) {
        return toAjax(recommendSwiperService.insertRecommendSwiper(recommendSwiper));
    }

    /**
     * 修改首页推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:edit')")
    @Log(title = "首页推荐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecommendSwiper recommendSwiper) {
        return toAjax(recommendSwiperService.updateRecommendSwiper(recommendSwiper));
    }

    /**
     * 删除首页推荐
     */
    @PreAuthorize("@ss.hasPermi('ci:swiper:remove')")
    @Log(title = "首页推荐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(recommendSwiperService.deleteRecommendSwiperByIds(ids));
    }
}
