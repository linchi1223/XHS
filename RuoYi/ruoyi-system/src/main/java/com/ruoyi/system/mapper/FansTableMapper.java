package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FansTable;

/**
 * fansMapper接口
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
public interface FansTableMapper 
{
    /**
     * 查询fans
     * 
     * @param fansid fansID
     * @return fans
     */
    public FansTable selectFansTableById(Long fansid);

    /**
     * 查询fans列表
     * 
     * @param fansTable fans
     * @return fans集合
     */
    public List<FansTable> selectFansTableList(FansTable fansTable);

    /**
     * 新增fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    public int insertFansTable(FansTable fansTable);

    /**
     * 修改fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    public int updateFansTable(FansTable fansTable);

    /**
     * 删除fans
     * 
     * @param fansid fansID
     * @return 结果
     */
    public int deleteFansTableById(Long fansid);

    /**
     * 批量删除fans
     * 
     * @param fansids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFansTableByIds(String[] fansids);
}
