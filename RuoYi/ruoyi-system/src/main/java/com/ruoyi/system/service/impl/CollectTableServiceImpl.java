package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CollectTableMapper;
import com.ruoyi.system.domain.CollectTable;
import com.ruoyi.system.service.ICollectTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户收藏管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Service
public class CollectTableServiceImpl implements ICollectTableService 
{
    @Autowired
    private CollectTableMapper collectTableMapper;

    /**
     * 查询用户收藏管理
     * 
     * @param collid 用户收藏管理ID
     * @return 用户收藏管理
     */
    @Override
    public CollectTable selectCollectTableById(Long collid)
    {
        return collectTableMapper.selectCollectTableById(collid);
    }

    /**
     * 查询用户收藏管理列表
     * 
     * @param collectTable 用户收藏管理
     * @return 用户收藏管理
     */
    @Override
    public List<CollectTable> selectCollectTableList(CollectTable collectTable)
    {
        return collectTableMapper.selectCollectTableList(collectTable);
    }

    /**
     * 新增用户收藏管理
     * 
     * @param collectTable 用户收藏管理
     * @return 结果
     */
    @Override
    public int insertCollectTable(CollectTable collectTable)
    {
        return collectTableMapper.insertCollectTable(collectTable);
    }

    /**
     * 修改用户收藏管理
     * 
     * @param collectTable 用户收藏管理
     * @return 结果
     */
    @Override
    public int updateCollectTable(CollectTable collectTable)
    {
        return collectTableMapper.updateCollectTable(collectTable);
    }

    /**
     * 删除用户收藏管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCollectTableByIds(String ids)
    {
        return collectTableMapper.deleteCollectTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户收藏管理信息
     * 
     * @param collid 用户收藏管理ID
     * @return 结果
     */
    @Override
    public int deleteCollectTableById(Long collid)
    {
        return collectTableMapper.deleteCollectTableById(collid);
    }
}
