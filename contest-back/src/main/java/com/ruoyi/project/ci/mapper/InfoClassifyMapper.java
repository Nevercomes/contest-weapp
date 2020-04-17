package com.ruoyi.project.ci.mapper;

import java.util.List;
import com.ruoyi.project.ci.domain.InfoClassify;

/**
 * 竞赛信息分类映射Mapper接口
 *
 * @author sun
 * @date 2020-04-17
 */
public interface InfoClassifyMapper {
    /**
     * 查询竞赛信息分类映射
     *
     * @param cpInfoId 竞赛信息分类映射ID
     * @return 竞赛信息分类映射
     */
    InfoClassify selectInfoClassifyById(Long cpInfoId);

    /**
     * 查询竞赛信息分类映射列表
     *
     * @param infoClassify 竞赛信息分类映射
     * @return 竞赛信息分类映射集合
     */
    List<InfoClassify> selectInfoClassifyList(InfoClassify infoClassify);

    /**
     * 新增竞赛信息分类映射
     *
     * @param infoClassify 竞赛信息分类映射
     * @return 结果
     */
    int insertInfoClassify(InfoClassify infoClassify);

    /**
     * 修改竞赛信息分类映射
     *
     * @param infoClassify 竞赛信息分类映射
     * @return 结果
     */
    int updateInfoClassify(InfoClassify infoClassify);

    /**
     * 删除竞赛信息分类映射
     *
     * @param cpInfoId 竞赛信息分类映射ID
     * @return 结果
     */
    int deleteInfoClassifyById(Long cpInfoId);

    /**
     * 批量删除竞赛信息分类映射
     *
     * @param cpInfoIds 需要删除的数据ID
     * @return 结果
     */
    int deleteInfoClassifyByIds(Long[] cpInfoIds);

    /**
     * 批量插入竞赛信息分类映射
     * @param list
     * @return
     */
    int batchInfoClassify(List<InfoClassify> list);

    /**
     * 根据竞赛Id批量删除分类
     * @param infoId
     */
    int deleteInfoClassifyByInfoId(Long infoId);
}
