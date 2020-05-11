package com.ruoyi.project.ci.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.ci.domain.SearchPopular;

/**
 * popularService接口
 *
 * @author sun
 * @date 2020-05-05
 */
public interface ISearchPopularService {
    /**
     * 查询popular
     *
     * @param id popularID
     * @return popular
     */
    SearchPopular selectSearchPopularById(Long id);

    /**
     * 查询popular列表
     *
     * @param searchPopular popular
     * @return popular集合
     */
    List<SearchPopular> selectSearchPopularList(SearchPopular searchPopular);

    /**
     * 查询popular选项
     *
     * @return Map 集合
     */
    List<Map<String, Object>> selectSearchPopularOptions(SearchPopular searchPopular);

    /**
     * 新增popular
     *
     * @param searchPopular popular
     * @return 结果
     */
    int insertSearchPopular(SearchPopular searchPopular);

    /**
     * 修改popular
     *
     * @param searchPopular popular
     * @return 结果
     */
    int updateSearchPopular(SearchPopular searchPopular);

    /**
     * 批量删除popular
     *
     * @param ids 需要删除的popularID
     * @return 结果
     */
    int deleteSearchPopularByIds(Long[] ids);

    /**
     * 删除popular信息
     *
     * @param id popularID
     * @return 结果
     */
    int deleteSearchPopularById(Long id);
}
