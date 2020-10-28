package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserTableMapper;
import com.ruoyi.system.domain.UserTable;
import com.ruoyi.system.service.IUserTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 普通用户管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-28
 */
@Service
public class UserTableServiceImpl implements IUserTableService 
{
    @Autowired
    private UserTableMapper userTableMapper;

    /**
     * 查询普通用户管理
     * 
     * @param userid 普通用户管理ID
     * @return 普通用户管理
     */
    @Override
    public UserTable selectUserTableById(Long userid)
    {
        return userTableMapper.selectUserTableById(userid);
    }

    /**
     * 查询普通用户管理列表
     * 
     * @param userTable 普通用户管理
     * @return 普通用户管理
     */
    @Override
    public List<UserTable> selectUserTableList(UserTable userTable)
    {
        return userTableMapper.selectUserTableList(userTable);
    }

    /**
     * 新增普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    @Override
    public int insertUserTable(UserTable userTable)
    {
        return userTableMapper.insertUserTable(userTable);
    }

    /**
     * 修改普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    @Override
    public int updateUserTable(UserTable userTable)
    {
        return userTableMapper.updateUserTable(userTable);
    }

    /**
     * 删除普通用户管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserTableByIds(String ids)
    {
        return userTableMapper.deleteUserTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除普通用户管理信息
     * 
     * @param userid 普通用户管理ID
     * @return 结果
     */
    @Override
    public int deleteUserTableById(Long userid)
    {
        return userTableMapper.deleteUserTableById(userid);
    }
}
