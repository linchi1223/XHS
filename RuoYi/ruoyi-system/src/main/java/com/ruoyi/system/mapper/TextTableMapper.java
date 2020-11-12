package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TextTable;

/**
 * 文章管理Mapper接口
 * 
 * @author zsh
 * @date 2020-10-29
 */
public interface TextTableMapper 
{
    /**
     * 查询文章管理
     * 
     * @param textid 文章管理ID
     * @return 文章管理
     */
    public TextTable selectTextTableById(Long textid);

    /**
     * 查询文章管理列表
     * 
     * @param textTable 文章管理
     * @return 文章管理集合
     */
    public List<TextTable> selectTextTableList(TextTable textTable);

    /**
     * 新增文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    public int insertTextTable(TextTable textTable);

    /**
     * 修改文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    public int updateTextTable(TextTable textTable);

    /**
     * 删除文章管理
     * 
     * @param textid 文章管理ID
     * @return 结果
     */
    public int deleteTextTableById(Long textid);

    /**
     * 批量删除文章管理
     * 
     * @param textids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTextTableByIds(String[] textids);
    /**
     * 查询文章管理
     *
     * @param userid 文章管理ID
     * @return 文章管理
     */
    public List<TextTable> selectTextTableByUserId(Long userid);
}
