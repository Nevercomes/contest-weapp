package com.ruoyi.project.ci.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 首页的竞赛达人和新建团队的数据结构
 * @author Sunss
 * @since 2020/8/12
 */
@Data
public class IndexFamous {

    /**
     * 用户头像、队伍头像
     */
    private String avatarUrl;

    /**
     * 队伍名、用户名称
     */
    private String name;

    /**
     * 类别，目前user、team
     */
    private String type;

    /**
     * 描述信息
     */
    private String desc;

}
