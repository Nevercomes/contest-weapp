package com.ruoyi.project.ci.controller;

import com.ruoyi.common.constant.DictConstant;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ci.domain.CompetitionPeriod;
import com.ruoyi.project.ci.domain.CompetitionTimeline;
import com.ruoyi.project.ci.domain.PostInfo;
import com.ruoyi.project.ci.domain.TeamInfo;
import com.ruoyi.project.ci.service.ICompetitionPeriodService;
import com.ruoyi.project.ci.service.ICompetitionTimelineService;
import com.ruoyi.project.ci.service.IPostInfoService;
import com.ruoyi.project.ci.service.ITeamInfoService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 真正执行搜索的controller
 *
 * @author Sunss
 * @since 2020/5/25
 */
@RestController
@RequestMapping("/search/result")
public class SearchIndexController extends BaseController {

    @Autowired
    private ICompetitionPeriodService competitionPeriodService;
    @Autowired
    private IPostInfoService postInfoService;
    @Autowired
    private ICompetitionTimelineService timelineService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ITeamInfoService teamInfoService;

    /**
     * 查询动态和竞赛
     */
//    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
//    @GetMapping("/all/{keyword}")
//    public AjaxResult listAll(@PathVariable("keyword") String keyword) {
//        AjaxResult result = AjaxResult.success();
//        CompetitionPeriod period = new CompetitionPeriod();
//        period.setName(keyword);
//        List<CompetitionPeriod> list = competitionPeriodService.selectCompetitionPeriodList(period);
//        PostInfo postInfo = new PostInfo();
//        postInfo.setName(keyword);
//        postInfo.setStatus(DictConstant.POST_STATUS_PUBLIC);
//        List<PostInfo> list1 = postInfoService.selectPostInfoList(postInfo);
//        result.put("comp", list);
//        result.put("post", list1);
//        // 异步保存搜索记录
//        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_ALL, SecurityUtils.getUsername()));
//        return result;
//    }

    /**
     * 查询竞赛
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/comp/{keyword}")
    public AjaxResult listComp(@PathVariable("keyword") String keyword) {
        AjaxResult result = AjaxResult.success();
        CompetitionPeriod period = new CompetitionPeriod();
        period.setName(keyword);
        List<CompetitionPeriod> list = competitionPeriodService.selectCompetitionPeriodList(period);
        result.put("comp", list);
        // 异步保存搜索记录
        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_COMP, SecurityUtils.getUsername()));
        return result;
    }

    /**
     * 查询动态
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/post/{keyword}")
    public AjaxResult listPost(@PathVariable("keyword") String keyword) {
        AjaxResult result = AjaxResult.success();
        PostInfo postInfo = new PostInfo();
        postInfo.setName(keyword);
        List<PostInfo> list1 = postInfoService.selectPostInfoList(postInfo);
        result.put("post", list1);
        // 异步保存搜索记录
        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_POST, SecurityUtils.getUsername()));
        return result;
    }

    /**
     * 查询资讯
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/timeline/{keyword}")
    public AjaxResult listTimeline(@PathVariable("keyword") String keyword) {
        AjaxResult result = AjaxResult.success();
        CompetitionTimeline timeline = new CompetitionTimeline();
        timeline.setContent(keyword);
        List<CompetitionTimeline> timelineList = timelineService.selectCompetitionTimelineList(timeline);
        result.put("timeline", timelineList);
        // 异步保存搜索记录
        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_TIMELINE, SecurityUtils.getUsername()));
        return result;
    }

    /**
     * 查询队伍
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/team/{keyword}")
    public AjaxResult listTeam(@PathVariable("keyword") String keyword) {
        AjaxResult result = AjaxResult.success();
        TeamInfo teamInfo = new TeamInfo();
        teamInfo.setName(keyword);
        List<TeamInfo> teamInfoList = teamInfoService.selectTeamInfoList(teamInfo);
        result.put("team", teamInfoList);
        // 异步保存搜索记录
        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_TEAM, SecurityUtils.getUsername()));
        return result;
    }

    /**
     * 查询用户
     */
    @PreAuthorize("@ss.hasPermi('ci:searchRecord:list')")
    @GetMapping("/user/{keyword}")
    public AjaxResult listUser(@PathVariable("keyword") String keyword) {
        AjaxResult result = AjaxResult.success();
        SysUser user = new SysUser();
        user.setTrueName(keyword);
        List<SysUser> userList = userService.selectUserList(user);
        result.put("user", userList);
        // 异步保存搜索记录
        AsyncManager.me().execute(AsyncFactory.recordSearch(keyword, DictConstant.SEARCH_TYPE_USER, SecurityUtils.getUsername()));
        return result;
    }

}
