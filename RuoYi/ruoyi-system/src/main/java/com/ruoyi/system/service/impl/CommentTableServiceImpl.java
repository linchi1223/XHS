package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommentTableMapper;
import com.ruoyi.system.domain.CommentTable;
import com.ruoyi.system.service.ICommentTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户评论管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Service
public class CommentTableServiceImpl implements ICommentTableService 
{
    @Autowired
    private CommentTableMapper commentTableMapper;

    /**
     * 查询用户评论管理
     * 
     * @param commentid 用户评论管理ID
     * @return 用户评论管理
     */
    @Override
    public CommentTable selectCommentTableById(Long commentid)
    {
        return commentTableMapper.selectCommentTableById(commentid);
    }

    /**
     * 查询用户评论管理
     *
     * @param textid 文章ID
     * @return 用户评论列表
     */
    public List<CommentTable> selectCommentTableByTextId(Long textid){
        return commentTableMapper.selectCommentTableByTextId(textid);
    }

    /**
     * 查询用户评论管理列表
     * 
     * @param commentTable 用户评论管理
     * @return 用户评论管理
     */
    @Override
    public List<CommentTable> selectCommentTableList(CommentTable commentTable)
    {
        return commentTableMapper.selectCommentTableList(commentTable);
    }

    /**
     * 新增用户评论管理
     * 
     * @param commentTable 用户评论管理
     * @return 结果
     */
    @Override
    public int insertCommentTable(CommentTable commentTable)
    {
        return commentTableMapper.insertCommentTable(commentTable);
    }

    /**
     * 修改用户评论管理
     * 
     * @param commentTable 用户评论管理
     * @return 结果
     */
    @Override
    public int updateCommentTable(CommentTable commentTable)
    {
        return commentTableMapper.updateCommentTable(commentTable);
    }

    /**
     * 删除用户评论管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommentTableByIds(String ids)
    {
        return commentTableMapper.deleteCommentTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户评论管理信息
     * 
     * @param commentid 用户评论管理ID
     * @return 结果
     */
    @Override
    public int deleteCommentTableById(Long commentid)
    {
        return commentTableMapper.deleteCommentTableById(commentid);
    }
}
