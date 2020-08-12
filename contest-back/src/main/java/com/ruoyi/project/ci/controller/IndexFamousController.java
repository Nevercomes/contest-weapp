package com.ruoyi.project.ci.controller;

import com.ruoyi.common.constant.DictConstant;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.ci.domain.IndexFamous;
import com.ruoyi.project.ci.domain.TeamExpect;
import com.ruoyi.project.ci.domain.TeamInfo;
import com.ruoyi.project.ci.domain.TeamInvite;
import com.ruoyi.project.ci.service.ITeamInfoService;
import com.ruoyi.project.ci.service.IUserTalentService;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunss
 * @since 2020/8/12
 */
@RestController
@RequestMapping("/ci/index/famous")
public class IndexFamousController extends BaseController {

    @Autowired
    private IUserTalentService userTalentService;

    @Autowired
    private ITeamInfoService teamInfoService;

    /**
     * 首页为了效率可以匿名访问，查询达人和新建队伍数据
     */
//    @PreAuthorize("@ss.hasPermi('ci:swiper:list')")
    @GetMapping("/list")
    public TableDataInfo list(IndexFamous indexFamous) {
        List<IndexFamous> list = new ArrayList<>();
        // 1.查询达人数据
        List<SysUser> userList = userTalentService.selectUserTalentList();
        for (SysUser user : userList) {
            IndexFamous userFamous = new IndexFamous();
            userFamous.setAvatarUrl(user.getAvatar());
            userFamous.setDesc("竞赛达人");
            userFamous.setName(user.getNickName());
            userFamous.setType(DictConstant.INDEX_FAMOUS_USER);
            userFamous.setItemId(user.getUserId());
            list.add(userFamous);
        }
        // 2.查询热门队伍
        startPage();
        List<TeamInfo> teamList = teamInfoService.selectTeamInfoList(new TeamInfo());
        for (TeamInfo team : teamList) {
            IndexFamous teamFamous = new IndexFamous();
            teamFamous.setAvatarUrl(team.getPicUrl());
            teamFamous.setDesc("新建团队");
            teamFamous.setName(team.getName());
            teamFamous.setType(DictConstant.INDEX_FAMOUS_TEAM);
            teamFamous.setItemId(team.getId());
            list.add(teamFamous);
        }
        return getDataTable(list);
    }


}
