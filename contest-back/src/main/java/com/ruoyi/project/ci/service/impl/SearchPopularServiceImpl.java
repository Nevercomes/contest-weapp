package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.SearchPopularMapper;
import com.ruoyi.project.ci.domain.SearchPopular;
import com.ruoyi.project.ci.service.ISearchPopularService;

/**
 * popularService业务层处理
 *
 * @author sun
 * @date 2020-05-05
 */
@Service
public class SearchPopularServiceImpl implements ISearchPopularService {
    @Autowired
    private SearchPopularMapper searchPopularMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询popular
     *
     * @param id popularID
     * @return popular
     */
    @Override
    public SearchPopular selectSearchPopularById(Long id) {
        return searchPopularMapper.selectSearchPopularById(id);
    }

    /**
     * 查询popular列表
     *
     * @param searchPopular popular
     * @return popular
     */
    @Override
    public List<SearchPopular> selectSearchPopularList(SearchPopular searchPopular) {
        return searchPopularMapper.selectSearchPopularList(searchPopular);
    }

    /**
     * 查询popular选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectSearchPopularOptions(SearchPopular searchPopular) {
        return searchPopularMapper.selectSearchPopularOptions(searchPopular);
    }

    /**
     * 新增popular
     *
     * @param searchPopular popular
     * @return 结果
     */
    @Override
    public int insertSearchPopular(SearchPopular searchPopular) {
        searchPopular.preInsert();
        return searchPopularMapper.insertSearchPopular(searchPopular);
    }

    /**
     * 修改popular
     *
     * @param searchPopular popular
     * @return 结果
     */
    @Override
    public int updateSearchPopular(SearchPopular searchPopular) {
        searchPopular.preUpdate();
        return searchPopularMapper.updateSearchPopular(searchPopular);
    }

    /**
     * 批量删除popular
     *
     * @param ids 需要删除的popularID
     * @return 结果
     */
    @Override
    public int deleteSearchPopularByIds(Long[] ids) {
        return searchPopularMapper.deleteSearchPopularByIds(ids);
    }

    /**
     * 删除popular信息
     *
     * @param id popularID
     * @return 结果
     */
    @Override
    public int deleteSearchPopularById(Long id) {
        return searchPopularMapper.deleteSearchPopularById(id);
    }

    /**
     * 注入创建者
     * @param searchPopular 注入对象
     */
    private void setCreated(SearchPopular searchPopular) {
        if(searchPopular == null) return;
        if (StringUtils.isNotEmpty(searchPopular.getCreateBy())) {
           // searchPopular.setCreateUser(userMapper.selectCreateUser(searchPopular.getCreateBy()));
        }
    }
}
