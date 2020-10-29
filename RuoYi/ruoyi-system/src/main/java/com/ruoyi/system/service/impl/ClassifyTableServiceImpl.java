package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ClassifyTableMapper;
import com.ruoyi.system.domain.ClassifyTable;
import com.ruoyi.system.service.IClassifyTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章标签管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-29
 */
@Service
public class ClassifyTableServiceImpl implements IClassifyTableService 
{
    @Autowired
    private ClassifyTableMapper classifyTableMapper;

    /**
     * 查询文章标签管理
     * 
     * @param csid 文章标签管理ID
     * @return 文章标签管理
     */
    @Override
    public ClassifyTable selectClassifyTableById(Long csid)
    {
        return classifyTableMapper.selectClassifyTableById(csid);
    }

    /**
     * 查询文章标签管理列表
     * 
     * @param classifyTable 文章标签管理
     * @return 文章标签管理
     */
    @Override
    public List<ClassifyTable> selectClassifyTableList(ClassifyTable classifyTable)
    {
        return classifyTableMapper.selectClassifyTableList(classifyTable);
    }

    /**
     * 新增文章标签管理
     * 
     * @param classifyTable 文章标签管理
     * @return 结果
     */
    @Override
    public int insertClassifyTable(ClassifyTable classifyTable)
    {
        return classifyTableMapper.insertClassifyTable(classifyTable);
    }

    /**
     * 修改文章标签管理
     * 
     * @param classifyTable 文章标签管理
     * @return 结果
     */
    @Override
    public int updateClassifyTable(ClassifyTable classifyTable)
    {
        return classifyTableMapper.updateClassifyTable(classifyTable);
    }

    /**
     * 删除文章标签管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassifyTableByIds(String ids)
    {
        return classifyTableMapper.deleteClassifyTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章标签管理信息
     * 
     * @param csid 文章标签管理ID
     * @return 结果
     */
    @Override
    public int deleteClassifyTableById(Long csid)
    {
        return classifyTableMapper.deleteClassifyTableById(csid);
    }
}
