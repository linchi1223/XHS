package com.ruoyi.system.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
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
    @Autowired
    private IFavorTableService favorTableService;
    /*
     * 数量更新
     * */
    /*
    * 数量变动
    * */
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
        if(flag) userinfoTable.setFans(userinfoTable.getFans()+1);
        else userinfoTable.setFans(userinfoTable.getFans()-1);
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
        if(flag)
            fansTableService.insertFansTable(fansTable);
        else
            fansTableService.deleteFansTableById(fansTableService.selectFansTableByUserid1AndUserid2(fansTable).getFansid());
        return true;
    }
    /*
    * @param commentable
    * 插入评论接口
    * */
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
    /*
    * 查询用户是否关注对应用户
    * */
    @GetMapping("/quere_fans")
    @ResponseBody
    public JSONObject if_fans(FansTable fansTable){
        JSONObject jsonObject = new JSONObject();
        FansTable fansTable1 = fansTableService.selectFansTableByUserid1AndUserid2(fansTable);
        if(fansTable1==null)
            jsonObject.put("result","false");
        else jsonObject.put("result","true");
        return jsonObject;
    }
    /*
    * 查询用户是否给对应文章点赞
    * */
    @GetMapping("/quere_favor")
    @ResponseBody
    public JSONObject if_favor(FavorTable favorTable){
        JSONObject jsonObject = new JSONObject();
        FavorTable favorTable1 = favorTableService.selectFavorTableByUseridAndTextid(favorTable);
        if(favorTable1==null)
            jsonObject.put("result","false");
        else jsonObject.put("result","true");
        return jsonObject;
    }
}
