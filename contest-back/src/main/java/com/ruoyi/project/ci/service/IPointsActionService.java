package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PointsAction;

/**
 * 积分记录Service接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface IPointsActionService {
    /**
     * 查询积分记录
     *
     * @param id 积分记录ID
     * @return 积分记录
     */
    PointsAction selectPointsActionById(Long id);

    /**
     * 查询积分记录列表
     *
     * @param pointsAction 积分记录
     * @return 积分记录集合
     */
    List<PointsAction> selectPointsActionList(PointsAction pointsAction);

    /**
     * 查询积分记录选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectPointsActionOptions(PointsAction pointsAction);

    /**
     * 新增积分记录
     *
     * @param pointsAction 积分记录
     * @return 结果
     */
    int insertPointsAction(PointsAction pointsAction);

    /**
     * 修改积分记录
     *
     * @param pointsAction 积分记录
     * @return 结果
     */
    int updatePointsAction(PointsAction pointsAction);

    /**
     * 批量删除积分记录
     *
     * @param ids 需要删除的积分记录ID
     * @return 结果
     */
    int deletePointsActionByIds(Long[] ids);

    /**
     * 删除积分记录信息
     *
     * @param id 积分记录ID
     * @return 结果
     */
    int deletePointsActionById(Long id);
}
