package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserinfoTable;

/**
 * countService接口
 * 
 * @author zsh
 * @date 2020-11-09
 */
public interface IUserinfoTableService 
{
    /**
     * 查询count
     * 
     * @param userid countID
     * @return count
     */
    public UserinfoTable selectUserinfoTableById(Long userid);

    /**
     * 查询count列表
     * 
     * @param userinfoTable count
     * @return count集合
     */
    public List<UserinfoTable> selectUserinfoTableList(UserinfoTable userinfoTable);

    /**
     * 新增count
     * 
     * @param userinfoTable count
     * @return 结果
     */
    public int insertUserinfoTable(UserinfoTable userinfoTable);

    /**
     * 修改count
     * 
     * @param userinfoTable count
     * @return 结果
     */
    public int updateUserinfoTable(UserinfoTable userinfoTable);

    /**
     * 批量删除count
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserinfoTableByIds(String ids);

    /**
     * 删除count信息
     * 
     * @param userid countID
     * @return 结果
     */
    public int deleteUserinfoTableById(Long userid);
}
