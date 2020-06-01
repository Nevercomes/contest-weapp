package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.RecommendSwiperMapper;
import com.ruoyi.project.ci.domain.RecommendSwiper;
import com.ruoyi.project.ci.service.IRecommendSwiperService;

/**
 * 首页推荐Service业务层处理
 *
 * @author sun
 * @date 2020-05-31
 */
@Service
public class RecommendSwiperServiceImpl implements IRecommendSwiperService {
    @Autowired
    private RecommendSwiperMapper recommendSwiperMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询首页推荐
     *
     * @param id 首页推荐ID
     * @return 首页推荐
     */
    @Override
    public RecommendSwiper selectRecommendSwiperById(Long id) {
        return recommendSwiperMapper.selectRecommendSwiperById(id);
    }

    /**
     * 查询首页推荐列表
     *
     * @param recommendSwiper 首页推荐
     * @return 首页推荐
     */
    @Override
    public List<RecommendSwiper> selectRecommendSwiperList(RecommendSwiper recommendSwiper) {
        return recommendSwiperMapper.selectRecommendSwiperList(recommendSwiper);
    }

    /**
     * 查询首页推荐选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectRecommendSwiperOptions(RecommendSwiper recommendSwiper) {
        return recommendSwiperMapper.selectRecommendSwiperOptions(recommendSwiper);
    }

    /**
     * 新增首页推荐
     *
     * @param recommendSwiper 首页推荐
     * @return 结果
     */
    @Override
    public int insertRecommendSwiper(RecommendSwiper recommendSwiper) {
        recommendSwiper.preInsert();
        return recommendSwiperMapper.insertRecommendSwiper(recommendSwiper);
    }

    /**
     * 修改首页推荐
     *
     * @param recommendSwiper 首页推荐
     * @return 结果
     */
    @Override
    public int updateRecommendSwiper(RecommendSwiper recommendSwiper) {
        recommendSwiper.preUpdate();
        return recommendSwiperMapper.updateRecommendSwiper(recommendSwiper);
    }

    /**
     * 批量删除首页推荐
     *
     * @param ids 需要删除的首页推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendSwiperByIds(Long[] ids) {
        return recommendSwiperMapper.deleteRecommendSwiperByIds(ids);
    }

    /**
     * 删除首页推荐信息
     *
     * @param id 首页推荐ID
     * @return 结果
     */
    @Override
    public int deleteRecommendSwiperById(Long id) {
        return recommendSwiperMapper.deleteRecommendSwiperById(id);
    }

    /**
     * 注入创建者
     * @param recommendSwiper 注入对象
     */
    private void setCreated(RecommendSwiper recommendSwiper) {
        if(recommendSwiper == null) return;
        if (StringUtils.isNotEmpty(recommendSwiper.getCreateBy())) {
           // recommendSwiper.setCreateUser(userMapper.selectCreateUser(recommendSwiper.getCreateBy()));
        }
    }
}
