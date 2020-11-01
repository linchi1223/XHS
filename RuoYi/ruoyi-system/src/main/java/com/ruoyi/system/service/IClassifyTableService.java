package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ClassifyTable;

/**
 * 文章标签管理Service接口
 * 
 * @author zsh
 * @date 2020-10-29
 */
public interface IClassifyTableService 
{
    /**
     * 查询文章标签管理
     * 
     * @param csid 文章标签管理ID
     * @return 文章标签管理
     */
    public ClassifyTable selectClassifyTableById(Long csid);

    /**
     * 查询文章标签管理列表
     * 
     * @param classifyTable 文章标签管理
     * @return 文章标签管理集合
     */
    public List<ClassifyTable> selectClassifyTableList(ClassifyTable classifyTable);

    /**
     * 新增文章标签管理
     * 
     * @param classifyTable 文章标签管理
     * @return 结果
     */
    public int insertClassifyTable(ClassifyTable classifyTable);

    /**
     * 修改文章标签管理
     * 
     * @param classifyTable 文章标签管理
     * @return 结果
     */
    public int updateClassifyTable(ClassifyTable classifyTable);

    /**
     * 批量删除文章标签管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassifyTableByIds(String ids);

    /**
     * 删除文章标签管理信息
     * 
     * @param csid 文章标签管理ID
     * @return 结果
     */
    public int deleteClassifyTableById(Long csid);
}
