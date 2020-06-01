package com.ruoyi.project.ci.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.RecommendSwiper;

/**
 * 首页推荐Mapper接口
 *
 * @author sun
 * @date 2020-05-31
 */
public interface RecommendSwiperMapper {
    /**
     * 查询首页推荐
     *
     * @param id 首页推荐ID
     * @return 首页推荐
     */
    RecommendSwiper selectRecommendSwiperById(Long id);

    /**
     * 查询首页推荐列表
     *
     * @param recommendSwiper 首页推荐
     * @return 首页推荐集合
     */
    List<RecommendSwiper> selectRecommendSwiperList(RecommendSwiper recommendSwiper);

    /**
     * 查询首页推荐选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectRecommendSwiperOptions(RecommendSwiper recommendSwiper);

    /**
     * 新增首页推荐
     *
     * @param recommendSwiper 首页推荐
     * @return 结果
     */
    int insertRecommendSwiper(RecommendSwiper recommendSwiper);

    /**
     * 修改首页推荐
     *
     * @param recommendSwiper 首页推荐
     * @return 结果
     */
    int updateRecommendSwiper(RecommendSwiper recommendSwiper);

    /**
     * 删除首页推荐
     *
     * @param id 首页推荐ID
     * @return 结果
     */
    int deleteRecommendSwiperById(Long id);

    /**
     * 批量删除首页推荐
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRecommendSwiperByIds(Long[] ids);
}
