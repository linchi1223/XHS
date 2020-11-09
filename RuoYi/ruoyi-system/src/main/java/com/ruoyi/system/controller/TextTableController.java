package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章管理Controller
 * 
 * @author zsh
 * @date 2020-10-29
 */
@Controller
@RequestMapping("/system/text_control")
public class TextTableController extends BaseController
{
    private String prefix = "system/text_control";

    @Autowired
    private ITextTableService textTableService;
    @Autowired
    private IUserTableService userTableService;
    @Autowired
    private IClassifyTableService classifyTableService;
    @Autowired
    private IUserinfoTableService userinfoTableService;
    @Autowired
    private ICommentTableService commentTableService;

    @RequiresPermissions("system:text_control:view")
    @GetMapping()
    public String text_control(UserTable userTable,ClassifyTable classifyTable,ModelMap mmap)
    {
        List<UserTable> userTables = userTableService.selectUserTableList(userTable);
        List<ClassifyTable> classifyTables = classifyTableService.selectClassifyTableList(classifyTable);
        mmap.put("user_list",userTables);
        mmap.put("classify_list",classifyTables);
        return prefix + "/text_control";
    }

    /**
     * 查询文章管理列表
     */
    @RequiresPermissions("system:text_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TextTable textTable, ClassifyTable classifyTable, ModelMap mmap, UserTable userTable)
    {
        List<ClassifyTable> classifyTables = classifyTableService.selectClassifyTableList(classifyTable);
        List<UserTable> userTables = userTableService.selectUserTableList(userTable);
        mmap.put("user_list",userTables);
        mmap.put("classify_list",classifyTables);
        startPage();
        List<TextTable> list = textTableService.selectTextTableList(textTable);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @RequiresPermissions("system:text_control:export")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TextTable textTable)
    {
        List<TextTable> list = textTableService.selectTextTableList(textTable);
        ExcelUtil<TextTable> util = new ExcelUtil<TextTable>(TextTable.class);
        return util.exportExcel(list, "text_control");
    }

    /**
     * 新增文章管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap, UserTable userTable, ClassifyTable classifyTable)
    {
        List<UserTable> userTables = userTableService.selectUserTableList(userTable);
        List<ClassifyTable> classifyTables = classifyTableService.selectClassifyTableList(classifyTable);
        mmap.put("user_list",userTables);
        mmap.put("classify_list",classifyTables);
        return prefix + "/add";
    }

    /**
     * 新增保存文章管理
     */
    @RequiresPermissions("system:text_control:add")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TextTable textTable)
    {
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(textTable.getUserid());
        userinfoTable.setFavor(userinfoTable.getTextCount()+1);
        userinfoTableService.updateUserinfoTable(userinfoTable);
        return toAjax(textTableService.insertTextTable(textTable));
    }

    /**
     * 修改文章管理
     */
    @GetMapping("/edit/{textid}")
    public String edit(@PathVariable("textid") Long textid, ModelMap mmap, UserTable userTable,ClassifyTable classifyTable)
    {
        TextTable textTable = textTableService.selectTextTableById(textid);
        List<UserTable> userTables = userTableService.selectUserTableList(userTable);
        List<ClassifyTable> classifyTables = classifyTableService.selectClassifyTableList(classifyTable);
        mmap.put("user_list",userTables);
        mmap.put("classify_list",classifyTables);
        mmap.put("textTable", textTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章管理
     */
    @RequiresPermissions("system:text_control:edit")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TextTable textTable)
    {
        return toAjax(textTableService.updateTextTable(textTable));
    }

    /**
     * 删除文章管理
     */
    @RequiresPermissions("system:text_control:remove")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        List<CommentTable> commentTables = commentTableService.selectCommentTableByTextId(Long.parseLong(ids));
        for(int i = 0;i<commentTables.size();++i)
            commentTableService.deleteCommentTableById(commentTables.get(i).getCommentid());
        return toAjax(textTableService.deleteTextTableByIds(ids));
    }
}
