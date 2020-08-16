package com.ruoyi.project.system.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.service.ISysUserSettingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sunss
 * @since 2020/8/14
 */
@RestController
@RequestMapping("/sys/userSetting")
public class SysUserSettingController extends BaseController {

    @Autowired
    private ISysUserSettingService userSettingService;

    @PutMapping("/onlySchool")
    public AjaxResult setOnlySchool(@RequestParam("onlySchool") boolean onlySchool) {
        return toAjax(userSettingService.updateOnlySchool(onlySchool));
    }

    @PutMapping("/msgNotice")
    public AjaxResult setMsgNotice(@RequestParam("msgNotice") boolean onlySchool) {
        return toAjax(userSettingService.updateMsgNotice(onlySchool));
    }

}
