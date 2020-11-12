package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserTable;

/**
 * 普通用户管理Mapper接口
 * 
 * @author zsh
 * @date 2020-10-30
 */
public interface UserTableMapper 
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
     * 删除普通用户管理
     * 
     * @param userid 普通用户管理ID
     * @return 结果
     */
    public int deleteUserTableById(Long userid);

    /**
     * 批量删除普通用户管理
     * 
     * @param userids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserTableByIds(String[] userids);

    /**
     * 根据用户手机号称获取用户
     * @param phone 需要查询的用户手机号
     * @return 结果
     */
    public UserTable selectUserTableByUserPhone(String phone);
}
