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
import com.ruoyi.system.domain.FavorTable;
import com.ruoyi.system.service.IFavorTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户点赞管理Controller
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/system/favor_control")
public class FavorTableController extends BaseController
{
    private String prefix = "system/favor_control";

    @Autowired
    private IFavorTableService favorTableService;

    @RequiresPermissions("system:favor_control:view")
    @GetMapping()
    public String favor_control()
    {
        return prefix + "/favor_control";
    }

    /**
     * 查询用户点赞管理列表
     */
    @RequiresPermissions("system:favor_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FavorTable favorTable)
    {
        startPage();
        List<FavorTable> list = favorTableService.selectFavorTableList(favorTable);
        return getDataTable(list);
    }

    /**
     * 导出用户点赞管理列表
     */
    @RequiresPermissions("system:favor_control:export")
    @Log(title = "用户点赞管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FavorTable favorTable)
    {
        List<FavorTable> list = favorTableService.selectFavorTableList(favorTable);
        ExcelUtil<FavorTable> util = new ExcelUtil<FavorTable>(FavorTable.class);
        return util.exportExcel(list, "favor_control");
    }

    /**
     * 新增用户点赞管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户点赞管理
     */
    @RequiresPermissions("system:favor_control:add")
    @Log(title = "用户点赞管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FavorTable favorTable)
    {
        return toAjax(favorTableService.insertFavorTable(favorTable));
    }

    /**
     * 修改用户点赞管理
     */
    @GetMapping("/edit/{favorid}")
    public String edit(@PathVariable("favorid") Long favorid, ModelMap mmap)
    {
        FavorTable favorTable = favorTableService.selectFavorTableById(favorid);
        mmap.put("favorTable", favorTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户点赞管理
     */
    @RequiresPermissions("system:favor_control:edit")
    @Log(title = "用户点赞管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FavorTable favorTable)
    {
        return toAjax(favorTableService.updateFavorTable(favorTable));
    }

    /**
     * 删除用户点赞管理
     */
    @RequiresPermissions("system:favor_control:remove")
    @Log(title = "用户点赞管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(favorTableService.deleteFavorTableByIds(ids));
    }
}
