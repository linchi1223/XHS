package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FavorTable;

/**
 * 用户点赞管理Mapper接口
 * 
 * @author zsh
 * @date 2020-10-22
 */
public interface FavorTableMapper 
{
    /**
     * 查询用户点赞管理
     * 
     * @param favorid 用户点赞管理ID
     * @return 用户点赞管理
     */
    public FavorTable selectFavorTableById(Long favorid);

    /**
     * 查询文章点赞数
     *
     * @param textid 用户点赞管理ID
     * @return 用户点赞管理
     */
    public List<FavorTable> selectFavorTableByTextId(Long textid);

    /**
     * 查询用户点赞管理列表
     * 
     * @param favorTable 用户点赞管理
     * @return 用户点赞管理集合
     */
    public List<FavorTable> selectFavorTableList(FavorTable favorTable);

    /**
     * 新增用户点赞管理
     * 
     * @param favorTable 用户点赞管理
     * @return 结果
     */
    public int insertFavorTable(FavorTable favorTable);

    /**
     * 修改用户点赞管理
     * 
     * @param favorTable 用户点赞管理
     * @return 结果
     */
    public int updateFavorTable(FavorTable favorTable);

    /**
     * 删除用户点赞管理
     * 
     * @param favorid 用户点赞管理ID
     * @return 结果
     */
    public int deleteFavorTableById(Long favorid);

    /**
     * 批量删除用户点赞管理
     * 
     * @param favorids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFavorTableByIds(String[] favorids);

    /**
     * 查询是否点赞
     *
     * @param favorTable 需要删除的数据ID
     * @return 结果
     */
    public FavorTable selectFavorTableByUseridAndTextid(FavorTable favorTable);

    /**
     * 查询是用户的点赞列表
     *
     * @param userid 需要查询的用户id
     * @return 结果
     */
    public List<FavorTable> selectFavorTableByUserid(Long userid);
}
