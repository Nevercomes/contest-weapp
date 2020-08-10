package com.ruoyi.project.ci.service.impl;

import java.util.List;
import java.util.Map;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ci.mapper.CompetitionCommentMapper;
import com.ruoyi.project.ci.domain.CompetitionComment;
import com.ruoyi.project.ci.service.ICompetitionCommentService;

/**
 * 竞赛评论Service业务层处理
 *
 * @author sun
 * @date 2020-08-09
 */
@Service
public class CompetitionCommentServiceImpl implements ICompetitionCommentService {
    @Autowired
    private CompetitionCommentMapper competitionCommentMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询竞赛评论
     *
     * @param id 竞赛评论ID
     * @return 竞赛评论
     */
    @Override
    public CompetitionComment selectCompetitionCommentById(Long id) {
        CompetitionComment comment = competitionCommentMapper.selectCompetitionCommentById(id);
        setCreated(comment);
        return comment;
    }

    /**
     * 查询竞赛评论列表
     *
     * @param competitionComment 竞赛评论
     * @return 竞赛评论
     */
    @Override
    public List<CompetitionComment> selectCompetitionCommentList(CompetitionComment competitionComment) {
        List<CompetitionComment> list = competitionCommentMapper.selectCompetitionCommentList(competitionComment);
        for (CompetitionComment comment : list) {
            setCreated(comment);
        }
        return list;
    }

    /**
     * 查询竞赛评论选项
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectCompetitionCommentOptions(CompetitionComment competitionComment) {
        return competitionCommentMapper.selectCompetitionCommentOptions(competitionComment);
    }

    /**
     * 新增竞赛评论
     *
     * @param competitionComment 竞赛评论
     * @return 结果
     */
    @Override
    public int insertCompetitionComment(CompetitionComment competitionComment) {
        competitionComment.preInsert();
        return competitionCommentMapper.insertCompetitionComment(competitionComment);
    }

    /**
     * 修改竞赛评论
     *
     * @param competitionComment 竞赛评论
     * @return 结果
     */
    @Override
    public int updateCompetitionComment(CompetitionComment competitionComment) {
        competitionComment.preUpdate();
        return competitionCommentMapper.updateCompetitionComment(competitionComment);
    }

    /**
     * 批量删除竞赛评论
     *
     * @param ids 需要删除的竞赛评论ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionCommentByIds(Long[] ids) {
        return competitionCommentMapper.deleteCompetitionCommentByIds(ids);
    }

    /**
     * 删除竞赛评论信息
     *
     * @param id 竞赛评论ID
     * @return 结果
     */
    @Override
    public int deleteCompetitionCommentById(Long id) {
        return competitionCommentMapper.deleteCompetitionCommentById(id);
    }

    /**
     * 注入创建者
     * @param competitionComment 注入对象
     */
    private void setCreated(CompetitionComment competitionComment) {
        if(competitionComment == null) return;
        if (StringUtils.isNotEmpty(competitionComment.getCreateBy())) {
            competitionComment.setCreateUser(userMapper.selectCreateUser(competitionComment.getCreateBy()));
        }
    }
}
