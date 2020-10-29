package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FavorTableMapper;
import com.ruoyi.system.domain.FavorTable;
import com.ruoyi.system.service.IFavorTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户点赞管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Service
public class FavorTableServiceImpl implements IFavorTableService 
{
    @Autowired
    private FavorTableMapper favorTableMapper;

    /**
     * 查询用户点赞管理
     * 
     * @param favorid 用户点赞管理ID
     * @return 用户点赞管理
     */
    @Override
    public FavorTable selectFavorTableById(Long favorid)
    {
        return favorTableMapper.selectFavorTableById(favorid);
    }

    /**
     * 查询用户点赞管理列表
     * 
     * @param favorTable 用户点赞管理
     * @return 用户点赞管理
     */
    @Override
    public List<FavorTable> selectFavorTableList(FavorTable favorTable)
    {
        return favorTableMapper.selectFavorTableList(favorTable);
    }

    /**
     * 新增用户点赞管理
     * 
     * @param favorTable 用户点赞管理
     * @return 结果
     */
    @Override
    public int insertFavorTable(FavorTable favorTable)
    {
        return favorTableMapper.insertFavorTable(favorTable);
    }

    /**
     * 修改用户点赞管理
     * 
     * @param favorTable 用户点赞管理
     * @return 结果
     */
    @Override
    public int updateFavorTable(FavorTable favorTable)
    {
        return favorTableMapper.updateFavorTable(favorTable);
    }

    /**
     * 删除用户点赞管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFavorTableByIds(String ids)
    {
        return favorTableMapper.deleteFavorTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户点赞管理信息
     * 
     * @param favorid 用户点赞管理ID
     * @return 结果
     */
    @Override
    public int deleteFavorTableById(Long favorid)
    {
        return favorTableMapper.deleteFavorTableById(favorid);
    }
}
