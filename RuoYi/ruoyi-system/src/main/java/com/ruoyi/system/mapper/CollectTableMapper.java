package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CollectTable;

/**
 * 用户收藏管理Mapper接口
 * 
 * @author zsh
 * @date 2020-10-22
 */
public interface CollectTableMapper 
{
    /**
     * 查询用户收藏管理
     * 
     * @param collid 用户收藏管理ID
     * @return 用户收藏管理
     */
    public CollectTable selectCollectTableById(Long collid);

    /**
     * 查询用户收藏管理列表
     * 
     * @param collectTable 用户收藏管理
     * @return 用户收藏管理集合
     */
    public List<CollectTable> selectCollectTableList(CollectTable collectTable);

    /**
     * 新增用户收藏管理
     * 
     * @param collectTable 用户收藏管理
     * @return 结果
     */
    public int insertCollectTable(CollectTable collectTable);

    /**
     * 修改用户收藏管理
     * 
     * @param collectTable 用户收藏管理
     * @return 结果
     */
    public int updateCollectTable(CollectTable collectTable);

    /**
     * 删除用户收藏管理
     * 
     * @param collid 用户收藏管理ID
     * @return 结果
     */
    public int deleteCollectTableById(Long collid);

    /**
     * 批量删除用户收藏管理
     * 
     * @param collids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCollectTableByIds(String[] collids);
}
