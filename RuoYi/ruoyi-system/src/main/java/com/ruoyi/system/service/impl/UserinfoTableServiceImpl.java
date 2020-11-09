package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.UserinfoTableMapper;
import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.IUserinfoTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * countService业务层处理
 * 
 * @author zsh
 * @date 2020-11-09
 */
@Service
public class UserinfoTableServiceImpl implements IUserinfoTableService 
{
    @Autowired
    private UserinfoTableMapper userinfoTableMapper;

    /**
     * 查询count
     * 
     * @param userid countID
     * @return count
     */
    @Override
    public UserinfoTable selectUserinfoTableById(Long userid)
    {
        return userinfoTableMapper.selectUserinfoTableById(userid);
    }

    /**
     * 查询count列表
     * 
     * @param userinfoTable count
     * @return count
     */
    @Override
    public List<UserinfoTable> selectUserinfoTableList(UserinfoTable userinfoTable)
    {
        return userinfoTableMapper.selectUserinfoTableList(userinfoTable);
    }

    /**
     * 新增count
     * 
     * @param userinfoTable count
     * @return 结果
     */
    @Override
    public int insertUserinfoTable(UserinfoTable userinfoTable)
    {
        return userinfoTableMapper.insertUserinfoTable(userinfoTable);
    }

    /**
     * 修改count
     * 
     * @param userinfoTable count
     * @return 结果
     */
    @Override
    public int updateUserinfoTable(UserinfoTable userinfoTable)
    {
        return userinfoTableMapper.updateUserinfoTable(userinfoTable);
    }

    /**
     * 删除count对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserinfoTableByIds(String ids)
    {
        return userinfoTableMapper.deleteUserinfoTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除count信息
     * 
     * @param userid countID
     * @return 结果
     */
    @Override
    public int deleteUserinfoTableById(Long userid)
    {
        return userinfoTableMapper.deleteUserinfoTableById(userid);
    }
}
