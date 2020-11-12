package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FansTable;

/**
 * fansService接口
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
public interface IFansTableService 
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
     * 批量删除fans
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFansTableByIds(String ids);

    /**
     * 删除fans信息
     * 
     * @param fansid fansID
     * @return 结果
     */
    public int deleteFansTableById(Long fansid);

    /**
     * 查询userid1是否关注userid2
     *
     * @param fansTable
     * @return 结果
     */
    public FansTable selectFansTableByUserid1AndUserid2(FansTable fansTable);

    /**
     * 查询userid的关注列表
     *
     * @param userid
     * @return 结果
     */
    public List<FansTable> selectFansTableByUserid(Long userid);
}
