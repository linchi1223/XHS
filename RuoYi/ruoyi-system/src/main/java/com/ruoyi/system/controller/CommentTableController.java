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
import com.ruoyi.system.domain.CommentTable;
import com.ruoyi.system.service.ICommentTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户评论管理Controller
 * 
 * @author zsh
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/system/comment_control")
public class CommentTableController extends BaseController
{
    private String prefix = "system/comment_control";

    @Autowired
    private ICommentTableService commentTableService;

    @RequiresPermissions("system:comment_control:view")
    @GetMapping()
    public String comment_control()
    {
        return prefix + "/comment_control";
    }

    /**
     * 查询用户评论管理列表
     */
    @RequiresPermissions("system:comment_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommentTable commentTable)
    {
        startPage();
        List<CommentTable> list = commentTableService.selectCommentTableList(commentTable);
        return getDataTable(list);
    }

    /**
     * 导出用户评论管理列表
     */
    @RequiresPermissions("system:comment_control:export")
    @Log(title = "用户评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CommentTable commentTable)
    {
        List<CommentTable> list = commentTableService.selectCommentTableList(commentTable);
        ExcelUtil<CommentTable> util = new ExcelUtil<CommentTable>(CommentTable.class);
        return util.exportExcel(list, "comment_control");
    }

    /**
     * 新增用户评论管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户评论管理
     */
    @RequiresPermissions("system:comment_control:add")
    @Log(title = "用户评论管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CommentTable commentTable)
    {
        return toAjax(commentTableService.insertCommentTable(commentTable));
    }

    /**
     * 修改用户评论管理
     */
    @GetMapping("/edit/{commentid}")
    public String edit(@PathVariable("commentid") Long commentid, ModelMap mmap)
    {
        CommentTable commentTable = commentTableService.selectCommentTableById(commentid);
        mmap.put("commentTable", commentTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户评论管理
     */
    @RequiresPermissions("system:comment_control:edit")
    @Log(title = "用户评论管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CommentTable commentTable)
    {
        return toAjax(commentTableService.updateCommentTable(commentTable));
    }

    /**
     * 删除用户评论管理
     */
    @RequiresPermissions("system:comment_control:remove")
    @Log(title = "用户评论管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commentTableService.deleteCommentTableByIds(ids));
    }
}
