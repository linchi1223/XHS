package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.mapper.UserinfoTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TextTableMapper;
import com.ruoyi.system.domain.TextTable;
import com.ruoyi.system.service.ITextTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-29
 */
@Service
public class TextTableServiceImpl implements ITextTableService 
{
    @Autowired
    private TextTableMapper textTableMapper;
    @Autowired
    private UserinfoTableMapper userinfoTableMapper;

    /**
     * 查询文章管理
     * 
     * @param textid 文章管理ID
     * @return 文章管理
     */
    @Override
    public TextTable selectTextTableById(Long textid)
    {
        return textTableMapper.selectTextTableById(textid);
    }

    /**
     * 查询文章管理列表
     * 
     * @param textTable 文章管理
     * @return 文章管理
     */
    @Override
    public List<TextTable> selectTextTableList(TextTable textTable)
    {
        return textTableMapper.selectTextTableList(textTable);
    }

    /**
     * 新增文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    @Override
    public int insertTextTable(TextTable textTable)
    {
        if(textTable.getUptime()==null)
            textTable.setUptime(new Date());
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(textTable.getUserid());
        userinfoTable.setTextCount(userinfoTable.getTextCount()+1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return textTableMapper.insertTextTable(textTable);
    }

    /**
     * 修改文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    @Override
    public int updateTextTable(TextTable textTable)
    {
        return textTableMapper.updateTextTable(textTable);
    }

    /**
     * 删除文章管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTextTableByIds(String ids)
    {
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(Long.parseLong(ids));
        userinfoTable.setTextCount(userinfoTable.getTextCount()-1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return textTableMapper.deleteTextTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章管理信息
     * 
     * @param textid 文章管理ID
     * @return 结果
     */
    @Override
    public int deleteTextTableById(Long textid)
    {
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(textid);
        userinfoTable.setTextCount(userinfoTable.getTextCount()-1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return textTableMapper.deleteTextTableById(textid);
    }
    /**
     * 查询文章管理
     *
     * @param userid 文章管理ID
     * @return 文章管理
     */
    public List<TextTable> selectTextTableByUserId(Long userid){
        return textTableMapper.selectTextTableByUserId(userid);
    }
}
