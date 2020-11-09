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
    @GetMapping("/updata_info_favor")
    @ResponseBody
    public Boolean updata_info_favor(Long userid){
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        userinfoTable.setFavor(userinfoTable.getFavor()+1);
        userinfoTableService.updateUserinfoTable(userinfoTable);
        return true;
    }
    @GetMapping("/updata_info_fans")
    @ResponseBody
    public Boolean updata_info_fans(Long userid){
        UserinfoTable userinfoTable = userinfoTableService.selectUserinfoTableById(userid);
        userinfoTable.setFavor(userinfoTable.getFans()+1);
        userinfoTableService.updateUserinfoTable(userinfoTable);
        return true;
    }
}
