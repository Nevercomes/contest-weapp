package com.ruoyi.project.system.controller;

import java.io.IOException;
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
import com.ruoyi.project.system.domain.SysOssRecord;
import com.ruoyi.project.system.service.ISysOssRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * OSS上传Controller
 *
 * @author sun
 * @date 2020-04-27
 */
@RestController
@RequestMapping("/system/ossRecord")
public class SysOssRecordController extends BaseController {
    @Autowired
    private ISysOssRecordService sysOssRecordService;

    /**
     * 查询OSS上传列表
     */
    @PreAuthorize("@ss.hasPermi('system:ossRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOssRecord sysOssRecord) {
        startPage();
        List<SysOssRecord> list = sysOssRecordService.selectSysOssRecordList(sysOssRecord);
        return getDataTable(list);
    }

    /**
     * 上传用户头像
     */
    @PreAuthorize("@ss.hasPermi('oss:upload:avatar')")
    @Log(title = "OSS记录-用户头像", businessType = BusinessType.INSERT)
    @RequestMapping("/upload/avatar")
    public AjaxResult uploadAvatar(MultipartFile file) throws IOException {
        return AjaxResult.success("上传成功", sysOssRecordService.uploadAvatar(file));
    }

    /**
     * 上传竞赛封面
     */
    @PreAuthorize("@ss.hasPermi('oss:upload:cover')")
    @Log(title = "OSS记录-竞赛封面", businessType = BusinessType.INSERT)
    @RequestMapping("/upload/cover")
    public AjaxResult uploadCover(MultipartFile file) throws IOException {
        return AjaxResult.success("上传成功", sysOssRecordService.uploadCpCover(file));
    }

    /**
     * 上传队伍头像
     */
    @PreAuthorize("@ss.hasPermi('oss:upload:team')")
    @Log(title = "OSS记录-队伍头像", businessType = BusinessType.INSERT)
    @RequestMapping("/upload/teamAvatar")
    public AjaxResult uploadTeamAvatar(MultipartFile file) throws IOException {
        return AjaxResult.success("上传成功", sysOssRecordService.uploadTeamAvatar(file));
    }

    /**
     * 上传首页Swiper的图片
     */
    @PreAuthorize("@ss.hasPermi('oss:upload:reco')")
    @Log(title = "OSS记录-首页Swiper", businessType = BusinessType.INSERT)
    @RequestMapping("/upload/recoSwiper")
    public AjaxResult uploadRecoSwiper(MultipartFile file) throws IOException {
        return AjaxResult.success("上传成功", sysOssRecordService.uploadRecoSwiper(file));
    }

    /**
     * 上传首页竞赛推荐的图片
     */
    @PreAuthorize("@ss.hasPermi('oss:upload:reco')")
    @Log(title = "OSS记录-首页竞赛推荐", businessType = BusinessType.INSERT)
    @RequestMapping("/upload/recoComp")
    public AjaxResult uploadRecoComp(MultipartFile file) throws IOException {
        return AjaxResult.success("上传成功", sysOssRecordService.uploadRecoComp(file));
    }


//    /**
//     * 导出OSS上传列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:ossRecord:export')")
//    @Log(title = "OSS上传", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(SysOssRecord sysOssRecord) {
//        List<SysOssRecord> list = sysOssRecordService.selectSysOssRecordList(sysOssRecord);
//        ExcelUtil<SysOssRecord> util = new ExcelUtil<SysOssRecord>(SysOssRecord.class);
//        return util.exportExcel(list, "ossRecord");
//    }

//    /**
//     * 获取OSS上传详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:ossRecord:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") String id) {
//        return AjaxResult.success(sysOssRecordService.selectSysOssRecordById(id));
//    }

//    /**
//     * 获取OSS上传选项信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:ossRecord:options')")
//    @GetMapping(value = "/options")
//    public AjaxResult options(SysOssRecord sysOssRecord) {
//        return AjaxResult.success(sysOssRecordService.selectSysOssRecordOptions(sysOssRecord));
//    }

//    /**
//     * 新增OSS上传
//     */
//    @PreAuthorize("@ss.hasPermi('system:ossRecord:add')")
//    @Log(title = "OSS上传", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody SysOssRecord sysOssRecord) {
//        return toAjax(sysOssRecordService.insertSysOssRecord(sysOssRecord));
//    }

//    /**
//     * 修改OSS上传
//     */
//    @PreAuthorize("@ss.hasPermi('system:ossRecord:edit')")
//    @Log(title = "OSS上传", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody SysOssRecord sysOssRecord) {
//        return toAjax(sysOssRecordService.updateSysOssRecord(sysOssRecord));
//    }

    /**
     * 删除OSS上传
     */
    @PreAuthorize("@ss.hasPermi('system:ossRecord:remove')")
    @Log(title = "OSS上传", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(sysOssRecordService.deleteSysOssRecordByIds(ids));
    }
}
