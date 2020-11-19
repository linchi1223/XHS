package com.ruoyi.system.common;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private ITextTableService textTableService;
    @Autowired
    private ICollectTableService collectTableService;
    /*
     * userid1关注用户
     * userid2被关注用户
     * flag 关注取关标志
     */
    @GetMapping("/updata_fans")
    @ResponseBody
    public JSONObject updata_fans(FansTable fansTable, boolean flag) {
        JSONObject jsonObject = new JSONObject();
        FansTable fansTable1 = fansTableService.selectFansTableByUserid1AndUserid2(fansTable);
        //flag为true 表示为关注
        if (flag&&fansTable1==null){
            jsonObject.put("result","true");
            fansTableService.insertFansTable(fansTable);
        }
        else if(!flag&&fansTable1!=null){
            jsonObject.put("result","false");
            fansTableService.deleteFansTableById(fansTableService.selectFansTableByUserid1AndUserid2(fansTable).getFansid());
        }
        else
            jsonObject.put("result","fail");
        return jsonObject;
    }
    /*
     * 传入的值为用户收藏的表 用到userid和textid
     * 用户收藏文章
     * */
    @GetMapping("/updata_collect")
    @ResponseBody
    public JSONObject collect_text(CollectTable collectTable, Boolean flag){
        JSONObject jsonObject = new JSONObject();
        CollectTable collectTable1 = collectTableService.selectCollectTableByUseridAndTextid(collectTable);
        if(flag&&collectTable1==null) {
            collectTableService.insertCollectTable(collectTable);
            jsonObject.put("result", "true");
        }
        else if(!flag&&collectTable1!=null){
            //删除收藏信息
            collectTableService.deleteCollectTableById(collectTable1.getCollid());
            jsonObject.put("result","false");
        }
        else jsonObject.put("result","fail");
        return jsonObject;
    }
    /*
    * 用户点赞文章
    * */
    @GetMapping("/updata_favor")
    @ResponseBody
    public JSONObject updata_favor(FavorTable favorTable, Boolean flag){
        JSONObject jsonObject = new JSONObject();
        FavorTable favorTable1 = favorTableService.selectFavorTableByUseridAndTextid(favorTable);
        if(flag&&favorTable1==null) {
            //用户点赞 并且数据库中不存在该用户点赞信息
            favorTableService.insertFavorTable(favorTable);
            jsonObject.put("result", "true");
        }
        else if(!flag&&favorTable1!=null){
            //删除点赞信息
           favorTableService.deleteFavorTableById(favorTable1.getFavorid());
            jsonObject.put("result","false");
        }
        else jsonObject.put("result","fail");
        return jsonObject;
    }
    /*
     * @param commentable
     * 插入评论接口
     * */
    @GetMapping("/insert_comment")
    @ResponseBody
    public JSONObject insert_comment(CommentTable commentTable) {
        System.out.println(commentTable);
        JSONObject jsonObject = new JSONObject();
        commentTable.setTime(new Date());
        int flag = commentTableService.insertCommentTable(commentTable);
        List<CommentTable> commentTables = commentTableService.selectCommentTableByTextId(commentTable.getTextid());
        for (int i = 0; i < commentTables.size(); ++i) {
            UserTable userTable1 = userTableService.selectUserTableById(commentTables.get(i).getUserid());
            commentTables.get(i).setcUser_n(userTable1.getUsername());
            commentTables.get(i).setcUser_p(userTable1.getPicture());
        }
        jsonObject.put("comment_list", commentTables);
        jsonObject.put("flag", flag);
        return jsonObject;
    }

    /*
     * 查询用户是否关注对应用户
     * */
    @GetMapping("/quere_fans")
    @ResponseBody
    public JSONObject if_fans(FansTable fansTable) {
        JSONObject jsonObject = new JSONObject();
        FansTable fansTable1 = fansTableService.selectFansTableByUserid1AndUserid2(fansTable);
        if (fansTable1 == null)
            jsonObject.put("result", "false");
        else jsonObject.put("result", "true");
        return jsonObject;
    }

    /*
     * 查询用户是否给对应文章点赞
     * */
    @GetMapping("/quere_favor")
    @ResponseBody
    public JSONObject if_favor(FavorTable favorTable) {
        JSONObject jsonObject = new JSONObject();
        FavorTable favorTable1 = favorTableService.selectFavorTableByUseridAndTextid(favorTable);
        if (favorTable1 == null)
            jsonObject.put("result", "false");
        else jsonObject.put("result", "true");
        return jsonObject;
    }
/*
* 发布文章接口
* */
    @CrossOrigin
    @GetMapping("/up_text")
    @ResponseBody
    public JSONObject up_text(TextTable textTable) {
        String str = textTable.getPicture();
        textTable.setPicture(str.substring(0,str.length()-1));
        textTable.setFavor(new Long(0));
        JSONObject jsonObject = new JSONObject();
        textTable.setUptime(new Date());
        int flag = textTableService.insertTextTable(textTable);
        if (flag == 1)
            jsonObject.put("result", "success");
        else jsonObject.put("result", "fail");
        return jsonObject;
    }
    /*
    * 用户删除文章
    * */
    @GetMapping("/delete_text")
    @ResponseBody
    public JSONObject delete_text(TextTable textTable) {
        int flag = textTableService.deleteTextTableById(textTable.getTextid());
        JSONObject jsonObject = new JSONObject();
        if (flag == 1)
            jsonObject.put("result", "success");
        else jsonObject.put("result", "fail");
        return jsonObject;
    }
    /*
    *
    * 查询用户是否收藏对应文章
    * */
    @GetMapping("/quere_collect")
    @ResponseBody
    public JSONObject quere_collect(CollectTable collectTable){
        JSONObject jsonObject = new JSONObject();
        CollectTable collectTable1 = collectTableService.selectCollectTableByUseridAndTextid(collectTable);
        if (collectTable1==null)
            jsonObject.put("result","false");
        else jsonObject.put("result","true");
        return jsonObject;
    }
}
