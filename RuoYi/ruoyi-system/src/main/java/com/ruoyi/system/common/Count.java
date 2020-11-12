package com.ruoyi.system.common;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.CommentTable;
import com.ruoyi.system.domain.FansTable;
import com.ruoyi.system.domain.UserTable;
import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.ICommentTableService;
import com.ruoyi.system.service.IFansTableService;
import com.ruoyi.system.service.IUserTableService;
import com.ruoyi.system.service.IUserinfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("common")
public class Count extends BaseController {
    /*
     * 获取用户粉丝数，关注数，文章数
     * */
    @Autowired
    private IUserinfoTableService userinfoTableService;
    @Autowired
    private IFansTableService fansTableService;
    @Autowired
    private ICommentTableService commentTableService;
    @Autowired
    private IUserTableService userTableService;
    /*
     * 数量更新
     * */
    /*
    * 数量变动
    * */
    @GetMapping("/updata_info_favor")
    @ResponseBody
    public Boolean updata_info_favor(Long userid,Boolean flag){
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        //用户信息返回空
        if(userinfoTable==null)
            return false;
        if(flag) userinfoTable.setFavor(userinfoTable.getFavor()+1);
        else userinfoTable.setFavor(userinfoTable.getFavor()-1);
        userinfoTableService.updateUserinfoTable(userinfoTable);
        return true;
    }
    /*
    * 数量变动
    * */
    public Boolean updata_info_fans(Long userid,Boolean flag){
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        if(userinfoTable==null)
            return true;
        if(flag) userinfoTable.setFavor(userinfoTable.getFans()+1);
        else userinfoTable.setFavor(userinfoTable.getFans()-1);
        userinfoTableService.updateUserinfoTable(userinfoTable);
        return true;
    }
    /*
    * userid1关注用户
    * userid2被关注用户
    * flag 关注取关标志
    */
    @GetMapping("/updata_info")
    @ResponseBody
    public Boolean updata_info(FansTable fansTable,boolean flag){
        //flag为true 表示为关注
        if(flag){
            //添加关注数据
            fansTableService.insertFansTable(fansTable);
            //关注用户 关注人数加一
            //被关注用户 粉丝数加一
            updata_info_favor(fansTable.getUserid1(),flag);
            updata_info_fans(fansTable.getUserid2(),flag);
        }
        else{
            //关注用户 关注人数减一
            //被关注用户 粉丝数减一
            updata_info_favor(fansTable.getUserid1(),flag);
            updata_info_fans(fansTable.getUserid2(),flag);
        }
        return true;
    }
    @GetMapping("/insert_comment")
    @ResponseBody
    public JSONObject insert_comment(CommentTable commentTable){
        System.out.println(commentTable);
        JSONObject jsonObject = new JSONObject();
        commentTable.setTime(new Date());
        int flag = commentTableService.insertCommentTable(commentTable);
        List<CommentTable> commentTables = commentTableService.selectCommentTableByTextId(commentTable.getTextid());
        for(int i = 0;i<commentTables.size();++i)
        {
            UserTable userTable1 = userTableService.selectUserTableById(commentTables.get(i).getUserid());
            commentTables.get(i).setcUser_n(userTable1.getUsername());
            commentTables.get(i).setcUser_p(userTable1.getPicture());
        }
        jsonObject.put("comment_list",commentTables);
        jsonObject.put("flag",flag);
        return  jsonObject;
    }
}
