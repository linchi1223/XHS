package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FavorTable;

/**
 * 用户点赞管理Service接口
 * 
 * @author zsh
 * @date 2020-10-22
 */
public interface IFavorTableService 
{
    /**
     * 查询用户点赞管理
     * 
     * @param favorid 用户点赞管理ID
     * @return 用户点赞管理
     */
    public FavorTable selectFavorTableById(Long favorid);

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
     * 批量删除用户点赞管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFavorTableByIds(String ids);

    /**
     * 删除用户点赞管理信息
     * 
     * @param favorid 用户点赞管理ID
     * @return 结果
     */
    public int deleteFavorTableById(Long favorid);
}
