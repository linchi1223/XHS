package com.ruoyi.system.common;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.service.IUserinfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("common")
public class Count extends BaseController {
    /*
     * 获取用户粉丝数，关注数，文章数
     * */
    @Autowired
    private IUserinfoTableService userinfoTableService;

    /*
     * 数量更新
     * */
    /*
    * 关注数量变动
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
    * 粉丝数量变动
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
    public Boolean updata_info(Long userid1,Long userid2,boolean flag){
        //flag为true 表示为关注
        if(flag){
            //关注用户 关注人数加一
            //被关注用户 粉丝数加一
            updata_info_favor(userid1,flag);
            updata_info_fans(userid2,flag);
        }
        else{
            //关注用户 关注人数减一
            //被关注用户 粉丝数减一
            updata_info_favor(userid1,flag);
            updata_info_fans(userid2,flag);
        }
        return true;
    }
}
