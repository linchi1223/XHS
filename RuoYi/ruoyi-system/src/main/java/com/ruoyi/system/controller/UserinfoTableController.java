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
import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.IUserinfoTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * countController
 * 
 * @author zsh
 * @date 2020-11-09
 */
@Controller
@RequestMapping("/system/count")
public class UserinfoTableController extends BaseController
{
    private String prefix = "system/count";

    @Autowired
    private IUserinfoTableService userinfoTableService;

    @RequiresPermissions("system:count:view")
    @GetMapping()
    public String count()
    {
        return prefix + "/count";
    }

    /**
     * 查询count列表
     */
    @RequiresPermissions("system:count:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserinfoTable userinfoTable)
    {
        startPage();
        List<UserinfoTable> list = userinfoTableService.selectUserinfoTableList(userinfoTable);
        return getDataTable(list);
    }

    /**
     * 导出count列表
     */
    @RequiresPermissions("system:count:export")
    @Log(title = "count", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserinfoTable userinfoTable)
    {
        List<UserinfoTable> list = userinfoTableService.selectUserinfoTableList(userinfoTable);
        ExcelUtil<UserinfoTable> util = new ExcelUtil<UserinfoTable>(UserinfoTable.class);
        return util.exportExcel(list, "count");
    }

    /**
     * 新增count
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存count
     */
    @RequiresPermissions("system:count:add")
    @Log(title = "count", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserinfoTable userinfoTable)
    {
        return toAjax(userinfoTableService.insertUserinfoTable(userinfoTable));
    }

    /**
     * 修改count
     */
    @GetMapping("/edit/{userid}")
    public String edit(@PathVariable("userid") Long userid, ModelMap mmap)
    {
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        mmap.put("userinfoTable", userinfoTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存count
     */
    @RequiresPermissions("system:count:edit")
    @Log(title = "count", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserinfoTable userinfoTable)
    {
        return toAjax(userinfoTableService.updateUserinfoTable(userinfoTable));
    }

    /**
     * 删除count
     */
    @RequiresPermissions("system:count:remove")
    @Log(title = "count", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userinfoTableService.deleteUserinfoTableByIds(ids));
    }
}
