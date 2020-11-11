package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.common.Count;
import com.ruoyi.system.domain.CommentTable;
import com.ruoyi.system.domain.TextTable;
import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.ICommentTableService;
import com.ruoyi.system.service.ITextTableService;
import com.ruoyi.system.service.IUserinfoTableService;
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
    @Autowired
    private ICommentTableService commentTableService;
    @Autowired
    private IUserinfoTableService userinfoTableService;

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
        int  a = userTableService.insertUserTable(userTable);
        Long userid = userTableService.selectUserTableByUserPhone(userTable.getPhone().toString()).getUserid();
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        if(userinfoTable==null){
            userinfoTable = new UserinfoTable();
            userinfoTable.setFans((long)0);
            userinfoTable.setFavor((long)0);
            userinfoTable.setUserid(userid);
            userinfoTable.setTextCount((long)0);
            userinfoTableService.insertUserinfoTable(userinfoTable);
        }
        return toAjax(a);
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
    public AjaxResult remove(String ids,TextTable textTable)
    {
       // 删除用户的文章
        List<TextTable> textTable1 = textTableService.selectTextTableList(textTable);
        for(int i = 0;i<textTable1.size();++i)
            if(textTable1.get(i).getUserid()==Long.parseLong(ids))
                textTableService.deleteTextTableById(textTable1.get(i).getTextid());
            //删除用户的点赞数，关注，文章信息
        userinfoTableService.deleteUserinfoTableById(Long.parseLong(ids));
        return toAjax(userTableService.deleteUserTableByIds(ids));
    }

    /**
     *
    *登录验证
    * */
    @CrossOrigin
    @GetMapping("/login/verify")
    @ResponseBody
    public JSONObject Login(String phone,String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","error");
        UserTable quereUser = userTableService.selectUserTableByUserPhone(phone);
        if (quereUser != null&&quereUser.getPassword().equals(password)) {
            jsonObject.set("data","success");
            jsonObject.put("userid",quereUser.getUserid());
        }
        return jsonObject;
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
    @GetMapping("/login/getTextList")
    @ResponseBody
    public TableDataInfo text_list(TextTable textTable){

        startPage();
        List<TextTable> textTable1 = textTableService.selectTextTableList(textTable);
        for(int i = 0;i<textTable1.size();++i){
            UserTable userTable = userTableService.selectUserTableById(textTable1.get(i).getUserid());
            textTable1.get(i).user_name = userTable.getUsername();
            textTable1.get(i).user_picture = userTable.getPicture();
        }
//        return textTable1;
        return getDataTable(textTable1);
    }

    @GetMapping("/login/getUserInfo")
    @ResponseBody
    public JSONObject User_Info(Long userid){
        UserTable userTable1 = userTableService.selectUserTableById(userid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("picture",userTable1.getPicture());
        jsonObject.put("username",userTable1.getUsername());
        return jsonObject;
    }

    @GetMapping("/login/getTextInfo")
    @ResponseBody
    public JSONObject Text_Info(Long textid){
        System.out.println(textid);
        TextTable textTable = textTableService.selectTextTableById(textid);
        UserTable userTable = userTableService.selectUserTableById(textTable.getUserid());
        List<CommentTable> commentTables = commentTableService.selectCommentTableByTextId(textid);
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(textTable.getUserid());
        JSONObject jsonObject = new JSONObject();
        //返回文章作者的用户名和头像
        jsonObject.put("username",userTable.getUsername());
        jsonObject.put("picture",userTable.getPicture());
        //返回文章作者用户的关注数，粉丝数，和文章数
        jsonObject.put("user_info",userinfoTable);
        //返回文章信息
        jsonObject.put("textinfo",textTable);
        /*
        *将文章评论对应用户的头像和用户名添加进去
        * */
        for(int i = 0;i<commentTables.size();++i)
        {
            UserTable userTable1 = userTableService.selectUserTableById(commentTables.get(i).getUserid());
            commentTables.get(i).setcUser_n(userTable1.getUsername());
            commentTables.get(i).setcUser_p(userTable1.getPicture());
        }
        jsonObject.put("comment_list",commentTables);
        return jsonObject;
    }
    @GetMapping("/login/getTextLList")
    @ResponseBody
    public TableDataInfo llist(UserTable userTable)
    {
        startPage();
        List<UserTable> list = userTableService.selectUserTableList(userTable);
        return getDataTable(list);
    }
}

