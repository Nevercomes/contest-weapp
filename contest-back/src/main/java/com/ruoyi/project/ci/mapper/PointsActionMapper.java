package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.PointsAction;
import org.apache.ibatis.annotations.Param;

/**
 * 积分记录Mapper接口
 *
 * @author sun
 * @date 2020-04-26
 */
public interface PointsActionMapper {
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
     * 删除积分记录
     *
     * @param id 积分记录ID
     * @return 结果
     */
    int deletePointsActionById(Long id);

    /**
     * 批量删除积分记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePointsActionByIds(Long[] ids);

    /**
     * 查询用户积分
     * @param userId
     * @return
     */
    PointsAction getUserPoints(@Param("userId") Long userId);
}
