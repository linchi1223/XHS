package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.common.Count;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    @Autowired
    private IFavorTableService favorTableService;
    @Autowired
    private IFansTableService fansTableService;
    @Autowired
    private IClassifyTableService classifyTableService;
    @Autowired
    private ICollectTableService collectTableService;
    @RequiresPermissions("system:commen_control:view")
    @GetMapping()
    public String commen_control()
    {
        return prefix + "/commen_control";
    }

    /**
     * 查询普通
     *
     * +管理列表
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
    public AjaxResult remove(String ids)
    {
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
    /*
     *  用户注册验证 添加用户信息到关注粉丝表
     */
    @GetMapping("/register/verify")
    @ResponseBody
    public String Register(UserTable userTable){
        System.out.println(userTable.toString());
        UserTable userTable1 = userTableService.selectUserTableByUserPhone(userTable.getPhone().toString());
        if(userTable1!=null)
            return "用户已经存在";
        int flag = userTableService.insertUserTable(userTable);
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
        if (flag == 1) {
            return "success";
        }
        else return "error";
    }
    /*
    * 获取文章列表 和用户的基本信息
    * */
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
    /*
    * 获取用户的 基本信息，关注，粉丝信息
    * */
    @GetMapping("/login/getUserInfo")
    @ResponseBody
    public JSONObject User_Info(Long userid){
        UserTable userTable1 = userTableService.selectUserTableById(userid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("picture",userTable1.getPicture());
        jsonObject.put("username",userTable1.getUsername());
        jsonObject.put("userinfo",userTable1);
        return jsonObject;
    }
/*
* 返回文章的详细信息和评论的列表
* */
    @GetMapping("/login/getTextInfo")
    @ResponseBody
    public JSONObject Text_Info(Long textid,Long userid1){
        System.out.println(textid);
        TextTable textTable = textTableService.selectTextTableById(textid);
        UserTable userTable = userTableService.selectUserTableById(textTable.getUserid());
        List<CommentTable> commentTables = commentTableService.selectCommentTableByTextId(textid);
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(textTable.getUserid());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",userTable.getUsername());
        jsonObject.put("picture",userTable.getPicture());
        jsonObject.put("textinfo",textTable);
        jsonObject.put("user_info",userinfoTable);
        for(int i = 0;i<commentTables.size();++i)
        {
            UserTable userTable1 = userTableService.selectUserTableById(commentTables.get(i).getUserid());
            commentTables.get(i).setcUser_n(userTable1.getUsername());
            commentTables.get(i).setcUser_p(userTable1.getPicture());
        }
        jsonObject.put("comment_list",commentTables);

        /*
        * 返回是否关注
        * */
        FansTable fansTable = new FansTable();
        fansTable.setUserid1(userid1);
        fansTable.setUserid2(textTable.getUserid());
        jsonObject.put("followed",if_fans(fansTable));

        /*
        * 返回是否点赞
        * */
        FavorTable favorTable = new FavorTable();
        favorTable.setUserid(userid1);
        favorTable.setTextid(textid);
        jsonObject.put("favored",if_favor(favorTable));

        /*
        * 返回是否收藏
        * */
        CollectTable collectTable = new CollectTable();
        collectTable.setUserid(userid1);
        collectTable.setTextid(textid);
        jsonObject.put("collected",quere_collect(collectTable));
//        boolean flag = fansTableService.selectFansTableById();
        return jsonObject;
    }
    /*
    * 分页获取文章列表
     */
    @GetMapping("/login/getTextLList")
    @ResponseBody
    public TableDataInfo llist(UserTable userTable)
    {
        startPage();
        List<UserTable> list = userTableService.selectUserTableList(userTable);
        return getDataTable(list);
    }
    /*
    * 用户修改个人信息
    * */
    @GetMapping("/login/editUserInfo")
    @ResponseBody
    public JSONObject user_edit(UserTable userTable){
        JSONObject jsonObject = new JSONObject();
        int flag = userTableService.updateUserTable(userTable);
        if(flag>0)
            jsonObject.put("result","success");
        else jsonObject.put("result","error");
        return jsonObject;
    }
    /*
    * 用户信息界面 包含用户信息和用户发布的文章列表
    * 通过用户id用户信息，和文章列表
    * */
    @GetMapping("/login/getUserText")
    @ResponseBody
    public JSONObject get_User_Text(Long userid){
        UserTable userTable1 = userTableService.selectUserTableById(userid);
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        JSONObject jsonObject = new JSONObject();
        List<TextTable>textTables = textTableService.selectTextTableByUserId(userid);
        jsonObject.put("picture",userTable1.getPicture());
        jsonObject.put("username",userTable1.getUsername());
        jsonObject.put("userinfoTable",userinfoTable);
        jsonObject.put("text_list",textTables);
        return jsonObject;
    }
    /*
    * 修改用户前  获取用户信息
    * */
    @GetMapping("/login/edituser")
    @ResponseBody
    public UserTable getUser(Long userid){
        return userTableService.selectUserTableById(userid);
    }

    /*
     * 用户上传前获取分类号
     * */
    @GetMapping("/login/getclassify")
    @ResponseBody
    public JSONObject getclassify(ClassifyTable classifyTable){
        JSONObject jsonObject = new JSONObject();
        List<ClassifyTable> classifyTables = classifyTableService.selectClassifyTableList(classifyTable);
        for(int i=0;i<classifyTables.size();++i){
            ArrayList<String> list = new ArrayList<>();
            list.add(classifyTables.get(i).getCsid().toString());
            list.add(classifyTables.get(i).getCname());
            jsonObject.put(""+i,list);
        }
        return jsonObject;
    }
    /*
     * 查询用户是否关注对应用户
     * */
    public Boolean if_fans(FansTable fansTable) {
        JSONObject jsonObject = new JSONObject();
        FansTable fansTable1 = fansTableService.selectFansTableByUserid1AndUserid2(fansTable);
        if (fansTable1 == null)
            return false;
        return true;
    }
    /*
    * 查询用户是否点赞
    * */
    public Boolean if_favor(FavorTable favorTable) {
        FavorTable favorTable1 = favorTableService.selectFavorTableByUseridAndTextid(favorTable);
        if (favorTable1 == null)
            return false;
        return true;
    }
    public Boolean quere_collect(CollectTable collectTable){
        CollectTable collectTable1 = collectTableService.selectCollectTableByUseridAndTextid(collectTable);
        if (collectTable1==null)
            return false;
        return true;
    }
}

