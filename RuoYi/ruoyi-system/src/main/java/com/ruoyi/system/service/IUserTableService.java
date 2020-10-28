package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.UserTable;

/**
 * 普通用户管理Service接口
 * 
 * @author zsh
 * @date 2020-10-28
 */
public interface IUserTableService 
{
    /**
     * 查询普通用户管理
     * 
     * @param userid 普通用户管理ID
     * @return 普通用户管理
     */
    public UserTable selectUserTableById(Long userid);

    /**
     * 查询普通用户管理列表
     * 
     * @param userTable 普通用户管理
     * @return 普通用户管理集合
     */
    public List<UserTable> selectUserTableList(UserTable userTable);

    /**
     * 新增普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    public int insertUserTable(UserTable userTable);

    /**
     * 修改普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    public int updateUserTable(UserTable userTable);

    /**
     * 批量删除普通用户管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserTableByIds(String ids);

    /**
     * 删除普通用户管理信息
     * 
     * @param userid 普通用户管理ID
     * @return 结果
     */
    public int deleteUserTableById(Long userid);
}
