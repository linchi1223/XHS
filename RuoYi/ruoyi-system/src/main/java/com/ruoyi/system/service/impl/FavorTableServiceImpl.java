package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.TextTable;
import com.ruoyi.system.mapper.TextTableMapper;
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
    @Autowired
    private TextTableMapper textTableMapper;
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
     * 查询文章点赞数
     *
     * @param textid 用户点赞管理ID
     * @return 用户点赞管理
     */
    public List<FavorTable> selectFavorTableByTextId(Long textid){
        return favorTableMapper.selectFavorTableByTextId(textid);
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
        favor_updata(favorTable,true);
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
        favor_updata(favorTableMapper.selectFavorTableById(Long.parseLong(ids)),false);
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
        favor_updata(favorTableMapper.selectFavorTableById(favorid),false);
        return favorTableMapper.deleteFavorTableById(favorid);
    }

    /**
     * 查询是否点赞
     *
     * @param favorTable 需要删除的数据ID
     * @return 结果
     */
    @Override
    public FavorTable selectFavorTableByUseridAndTextid(FavorTable favorTable){
        return favorTableMapper.selectFavorTableByUseridAndTextid(favorTable);
    }

    /**
     * 查询是用户的点赞列表
     *
     * @param userid 需要查询的用户id
     * @return 结果
     */
    @Override
    public List<FavorTable> selectFavorTableByUserid(Long userid){
        return favorTableMapper.selectFavorTableByUserid(userid);
    }

    //文章的点赞数更新
    public void favor_updata(FavorTable favorTable,boolean flag){
       TextTable textTable = textTableMapper.selectTextTableById(favorTable.getTextid());
       if(flag)
            textTable.setFavor(textTable.getFavor()+1);
        else textTable.setFavor(textTable.getFavor()-1);
        textTableMapper.updateTextTable(textTable);
    }

}
