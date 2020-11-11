package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FansTableMapper;
import com.ruoyi.system.domain.FansTable;
import com.ruoyi.system.service.IFansTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * fansService业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
@Service
public class FansTableServiceImpl implements IFansTableService 
{
    @Autowired
    private FansTableMapper fansTableMapper;

    /**
     * 查询fans
     * 
     * @param fansid fansID
     * @return fans
     */
    @Override
    public FansTable selectFansTableById(Long fansid)
    {
        return fansTableMapper.selectFansTableById(fansid);
    }

    /**
     * 查询fans列表
     * 
     * @param fansTable fans
     * @return fans
     */
    @Override
    public List<FansTable> selectFansTableList(FansTable fansTable)
    {
        return fansTableMapper.selectFansTableList(fansTable);
    }

    /**
     * 新增fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    @Override
    public int insertFansTable(FansTable fansTable)
    {
        return fansTableMapper.insertFansTable(fansTable);
    }

    /**
     * 修改fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    @Override
    public int updateFansTable(FansTable fansTable)
    {
        return fansTableMapper.updateFansTable(fansTable);
    }

    /**
     * 删除fans对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFansTableByIds(String ids)
    {
        return fansTableMapper.deleteFansTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除fans信息
     * 
     * @param fansid fansID
     * @return 结果
     */
    @Override
    public int deleteFansTableById(Long fansid)
    {
        return fansTableMapper.deleteFansTableById(fansid);
    }
}
