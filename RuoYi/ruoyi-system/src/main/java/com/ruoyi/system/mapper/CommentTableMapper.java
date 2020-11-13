package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CommentTable;

/**
 * 用户评论管理Mapper接口
 * 
 * @author zsh
 * @date 2020-10-22
 */
public interface CommentTableMapper 
{
    /**
     * 查询用户评论管理
     * 
     * @param commentid 用户评论管理ID
     * @return 用户评论管理
     */
    public CommentTable selectCommentTableById(Long commentid);

    /**
     * 查询用户评论管理
     *
     * @param textid 文章ID
     * @return 用户评论列表
     */
     public List<CommentTable> selectCommentTableByTextId(Long textid);

    /**
     * 查询用户评论管理列表
     * 
     * @param commentTable 用户评论管理
     * @return 用户评论管理集合
     */
    public List<CommentTable> selectCommentTableList(CommentTable commentTable);

    /**
     * 新增用户评论管理
     * 
     * @param commentTable 用户评论管理
     * @return 结果
     */
    public int insertCommentTable(CommentTable commentTable);

    /**
     * 修改用户评论管理
     * 
     * @param commentTable 用户评论管理
     * @return 结果
     */
    public int updateCommentTable(CommentTable commentTable);

    /**
     * 删除用户评论管理
     * 
     * @param commentid 用户评论管理ID
     * @return 结果
     */
    public int deleteCommentTableById(Long commentid);

    /**
     * 批量删除用户评论管理
     * 
     * @param commentids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentTableByIds(String[] commentids);

    /**
     * 通过用户id获取用户的评论列表
     *
     * @param userid 需要查询的用户id
     * @return 结果
     */
    public List<CommentTable> selectCommentTableByUserId(Long userid);
}
