package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CollectTable;
import com.ruoyi.system.service.ICollectTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏管理Controller
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/system/collect_control")
public class CollectTableController extends BaseController
{
    private String prefix = "system/collect_control";

    @Autowired
    private ICollectTableService collectTableService;

    @GetMapping()
    public String collect_control()
    {
        return prefix + "/collect_control";
    }

    /**
     * 查询用户收藏管理列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CollectTable collectTable)
    {
        startPage();
        List<CollectTable> list = collectTableService.selectCollectTableList(collectTable);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏管理列表
     */
    @Log(title = "用户收藏管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CollectTable collectTable)
    {
        List<CollectTable> list = collectTableService.selectCollectTableList(collectTable);
        ExcelUtil<CollectTable> util = new ExcelUtil<CollectTable>(CollectTable.class);
        return util.exportExcel(list, "collect_control");
    }

    /**
     * 新增用户收藏管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户收藏管理
     */
    @Log(title = "用户收藏管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CollectTable collectTable)
    {
        return toAjax(collectTableService.insertCollectTable(collectTable));
    }

    /**
     * 修改用户收藏管理
     */
    @GetMapping("/edit/{collid}")
    public String edit(@PathVariable("collid") Long collid, ModelMap mmap)
    {
        CollectTable collectTable = collectTableService.selectCollectTableById(collid);
        mmap.put("collectTable", collectTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户收藏管理
     */
    @Log(title = "用户收藏管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CollectTable collectTable)
    {
        return toAjax(collectTableService.updateCollectTable(collectTable));
    }

    /**
     * 删除用户收藏管理
     */
    @Log(title = "用户收藏管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(collectTableService.deleteCollectTableByIds(ids));
    }
}
