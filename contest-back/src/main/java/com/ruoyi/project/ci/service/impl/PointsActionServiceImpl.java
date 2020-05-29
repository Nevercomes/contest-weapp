package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.PointsActionMapper;
import com.ruoyi.project.ci.domain.PointsAction;
import com.ruoyi.project.ci.service.IPointsActionService;

/**
 * 积分记录Service业务层处理
 *
 * @author sun
 * @date 2020-04-26
 */
@Service
public class PointsActionServiceImpl implements IPointsActionService {
    @Autowired
    private PointsActionMapper pointsActionMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询积分记录
     *
     * @param id 积分记录ID
     * @return 积分记录
     */
    @Override
    public PointsAction selectPointsActionById(Long id) {
        return pointsActionMapper.selectPointsActionById(id);
    }

    /**
     * 查询积分记录列表
     *
     * @param pointsAction 积分记录
     * @return 积分记录
     */
    @Override
    public List<PointsAction> selectPointsActionList(PointsAction pointsAction) {
        return pointsActionMapper.selectPointsActionList(pointsAction);
    }

    /**
     * 查询积分记录选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectPointsActionOptions(PointsAction pointsAction) {
        return pointsActionMapper.selectPointsActionOptions(pointsAction);
    }

    /**
     * 新增积分记录
     *
     * @param pointsAction 积分记录
     * @return 结果
     */
    @Override
    public int insertPointsAction(PointsAction pointsAction) {
        pointsAction.preInsert();
        return pointsActionMapper.insertPointsAction(pointsAction);
    }

    /**
     * 修改积分记录
     *
     * @param pointsAction 积分记录
     * @return 结果
     */
    @Override
    public int updatePointsAction(PointsAction pointsAction) {
        pointsAction.preUpdate();
        return pointsActionMapper.updatePointsAction(pointsAction);
    }

    /**
     * 批量删除积分记录
     *
     * @param ids 需要删除的积分记录ID
     * @return 结果
     */
    @Override
    public int deletePointsActionByIds(Long[] ids) {
        return pointsActionMapper.deletePointsActionByIds(ids);
    }

    /**
     * 删除积分记录信息
     *
     * @param id 积分记录ID
     * @return 结果
     */
    @Override
    public int deletePointsActionById(Long id) {
        return pointsActionMapper.deletePointsActionById(id);
    }

    /**
     * 查询用户积分
     * @param userId
     * @return
     */
    @Override
    public PointsAction getUserPoints(Long userId) {
        return pointsActionMapper.getUserPoints(userId);
    }

    private void setAssociated(PointsAction pointsAction) {
        if(pointsAction == null) return;
        if (pointsAction.getUserId() == null) {
            pointsAction.setAssUser(userMapper.selectAssUser(pointsAction.getUserId()));
        }
    }
}
