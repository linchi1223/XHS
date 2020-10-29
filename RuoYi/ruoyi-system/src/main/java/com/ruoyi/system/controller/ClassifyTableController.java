package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ClassifyTable;
import com.ruoyi.system.service.IClassifyTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章标签管理Controller
 * 
 * @author zsh
 * @date 2020-10-25
 */
@Controller
@RequestMapping("/system/classify_control")
public class  ClassifyTableController extends BaseController
{
    private String prefix = "system/classify_control";

    @Autowired
    private IClassifyTableService classifyTableService;

    @RequiresPermissions("system:classify_control:view")
    @GetMapping()
    public String classify_control()
    {
        return prefix + "/classify_control";
    }

    /**
     * 查询文章标签管理列表
     */
    @RequiresPermissions("system:classify_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClassifyTable classifyTable)
    {
        startPage();
        List<ClassifyTable> list = classifyTableService.selectClassifyTableList(classifyTable);
        return getDataTable(list);
    }

    /**
     * 导出文章标签管理列表
     */
    @RequiresPermissions("system:classify_control:export")
    @Log(title = "文章标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClassifyTable classifyTable)
    {
        List<ClassifyTable> list = classifyTableService.selectClassifyTableList(classifyTable);
        ExcelUtil<ClassifyTable> util = new ExcelUtil<ClassifyTable>(ClassifyTable.class);
        return util.exportExcel(list, "classify_control");
    }

    /**
     * 新增文章标签管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文章标签管理
     */
    @RequiresPermissions("system:classify_control:add")
    @Log(title = "文章标签管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClassifyTable classifyTable)
    {
        return toAjax(classifyTableService.insertClassifyTable(classifyTable));
    }

    /**
     * 修改文章标签管理
     */
    @GetMapping("/edit/{csid}")
    public String edit(@PathVariable("csid") Long csid, ModelMap mmap)
    {
        ClassifyTable classifyTable = classifyTableService.selectClassifyTableById(csid);
        mmap.put("classifyTable", classifyTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章标签管理
     */
    @RequiresPermissions("system:classify_control:edit")
    @Log(title = "文章标签管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClassifyTable classifyTable)
    {
        return toAjax(classifyTableService.updateClassifyTable(classifyTable));
    }

    /**
     * 删除文章标签管理
     */
    @RequiresPermissions("system:classify_control:remove")
    @Log(title = "文章标签管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classifyTableService.deleteClassifyTableByIds(ids));
    }
}
