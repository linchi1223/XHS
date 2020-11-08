package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.service.ITextTableService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.UserTable;
import com.ruoyi.system.service.IUserTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.jws.soap.SOAPBinding;

/**
 * 普通用户管理Controller
 * 
 * @author zsh
 * @date 2020-10-30
 */
@Controller
@RequestMapping("/system/commen_control")
public class UserTableController extends BaseController
{
    private String prefix = "system/commen_control";

    @Autowired
    private IUserTableService userTableService;
    @Autowired
    private ITextTableService textTableService;

    @RequiresPermissions("system:commen_control:view")
    @GetMapping()
    public String commen_control()
    {
        return prefix + "/commen_control";
    }

    /**
     * 查询普通用户管理列表
     */
    @RequiresPermissions("system:commen_control:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserTable userTable)
    {
        startPage();
        List<UserTable> list = userTableService.selectUserTableList(userTable);
        return getDataTable(list);
    }

    /**
     * 导出普通用户管理列表
     */
    @RequiresPermissions("system:commen_control:export")
    @Log(title = "普通用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserTable userTable)
    {
        List<UserTable> list = userTableService.selectUserTableList(userTable);
        ExcelUtil<UserTable> util = new ExcelUtil<UserTable>(UserTable.class);
        return util.exportExcel(list, "commen_control");
    }

    /**
     * 新增普通用户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存普通用户管理
     */
    @RequiresPermissions("system:commen_control:add")
    @Log(title = "普通用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserTable userTable)
    {
        return toAjax(userTableService.insertUserTable(userTable));
    }

    /**
     * 修改普通用户管理
     */
    @GetMapping("/edit/{userid}")
    public String edit(@PathVariable("userid") Long userid, ModelMap mmap)
    {
        UserTable userTable = userTableService.selectUserTableById(userid);
        mmap.put("userTable", userTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存普通用户管理
     */
    @RequiresPermissions("system:commen_control:edit")
    @Log(title = "普通用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserTable userTable)
    {
        return toAjax(userTableService.updateUserTable(userTable));
    }

    /**
     * 删除普通用户管理
     */
    @RequiresPermissions("system:commen_control:remove")
    @Log(title = "普通用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //删除用户的文章
//        textTableService.selectTextTableList()
//        textTableService.deleteTextTableById();
        return toAjax(userTableService.deleteUserTableByIds(ids));
    }

    /**
     *
    *登录验证
    * */
    @CrossOrigin
    @GetMapping("/login/verify")
    @ResponseBody
    public String Login(String phone,String password){
        System.out.println(phone+" "+password);
        UserTable quereUser = userTableService.selectUserTableByUserPhone(phone);
        if (quereUser != null&&quereUser.getPassword().equals(password)) {
            System.out.println(quereUser.toString());
            return "success";
        }
        else return "error";
    }

    @GetMapping("/register/verify")
    @ResponseBody
    public String Register(UserTable userTable){
        System.out.println(userTable.toString());
        UserTable userTable1 = userTableService.selectUserTableByUserPhone(userTable.getPhone().toString());
        if(userTable1!=null)
            return "用户已经存在";
        int flag = userTableService.insertUserTable(userTable);
        if (flag == 1) {
            return "success";
        }
        else return "error";
    }
}
