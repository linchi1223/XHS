package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.IUserTableService;
import com.ruoyi.system.service.IUserinfoTableService;
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
import com.ruoyi.system.domain.FansTable;
import com.ruoyi.system.service.IFansTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * fansController
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
@Controller
@RequestMapping("/system/fans_control")
public class FansTableController extends BaseController
{
    private String prefix = "system/fans_control";

    @Autowired
    private IFansTableService fansTableService;
    @Autowired
    private IUserinfoTableService userinfoTableService;
    @RequiresPermissions("system:fans_control:view")
    @GetMapping()
    public String fans_control()
    {
        return prefix + "/fans_control";
    }

    /**
     * 查询fans列表
     */
    @RequiresPermissions("system:fans_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FansTable fansTable)
    {
        startPage();
        List<FansTable> list = fansTableService.selectFansTableList(fansTable);
        return getDataTable(list);
    }

    /**
     * 导出fans列表
     */
    @RequiresPermissions("system:fans_control:export")
    @Log(title = "fans", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FansTable fansTable)
    {
        List<FansTable> list = fansTableService.selectFansTableList(fansTable);
        ExcelUtil<FansTable> util = new ExcelUtil<FansTable>(FansTable.class);
        return util.exportExcel(list, "fans_control");
    }

    /**
     * 新增fans
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存fans
     */
    @RequiresPermissions("system:fans_control:add")
    @Log(title = "fans", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FansTable fansTable)
    {
        return toAjax(fansTableService.insertFansTable(fansTable));
    }

    /**
     * 修改fans
     */
    @GetMapping("/edit/{fansid}")
    public String edit(@PathVariable("fansid") Long fansid, ModelMap mmap)
    {
        FansTable fansTable = fansTableService.selectFansTableById(fansid);
        mmap.put("fansTable", fansTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存fans
     */
    @RequiresPermissions("system:fans_control:edit")
    @Log(title = "fans", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FansTable fansTable)
    {
        return toAjax(fansTableService.updateFansTable(fansTable));
    }

    /**
     * 删除fans
     */
    @RequiresPermissions("system:fans_control:remove")
    @Log(title = "fans", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fansTableService.deleteFansTableByIds(ids));
    }
}
