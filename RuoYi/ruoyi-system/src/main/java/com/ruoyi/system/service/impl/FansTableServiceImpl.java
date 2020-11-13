package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.UserinfoTable;
import com.ruoyi.system.mapper.UserinfoTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FansTableMapper;
import com.ruoyi.system.domain.FansTable;
import com.ruoyi.system.service.IFansTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * fansService业务层处理
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
@Service
public class FansTableServiceImpl implements IFansTableService 
{
    @Autowired
    private FansTableMapper fansTableMapper;
    @Autowired
    private UserinfoTableMapper userinfoTableMapper;
    /**
     * 查询fans
     * 
     * @param fansid fansID
     * @return fans
     */
    @Override
    public FansTable selectFansTableById(Long fansid)
    {
        return fansTableMapper.selectFansTableById(fansid);
    }

    /**
     * 查询fans列表
     * 
     * @param fansTable fans
     * @return fans
     */
    @Override
    public List<FansTable> selectFansTableList(FansTable fansTable)
    {
        return fansTableMapper.selectFansTableList(fansTable);
    }

    /**
     * 新增fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    @Override
    public int insertFansTable(FansTable fansTable)
    {
        updata_info(fansTable,true);
        return fansTableMapper.insertFansTable(fansTable);
    }

    /**
     * 修改fans
     * 
     * @param fansTable fans
     * @return 结果
     */
    @Override
    public int updateFansTable(FansTable fansTable)
    {
        return fansTableMapper.updateFansTable(fansTable);
    }

    /**
     * 删除fans对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFansTableByIds(String ids)
    {
        updata_info(fansTableMapper.selectFansTableById(Long.parseLong(ids)),false);
        return fansTableMapper.deleteFansTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除fans信息
     * 
     * @param fansid fansID
     * @return 结果
     */
    @Override
    public int deleteFansTableById(Long fansid)
    {
        updata_info(fansTableMapper.selectFansTableById(fansid),false);
        return fansTableMapper.deleteFansTableById(fansid);
    }

    /**
     * 查询userid1是否关注userid2
     *
     * @param fansTable
     * @return 结果
     */
    public FansTable selectFansTableByUserid1AndUserid2(FansTable fansTable){
        return fansTableMapper.selectFansTableByUserid1AndUserid2(fansTable);
    }
    /**
     * 查询userid的关注列表
     *
     * @param userid
     * @return 结果
     */
    public List<FansTable> selectFansTableByUserid(Long userid){
        return fansTableMapper.selectFansTableByUserid(userid);
    }



    public Boolean updata_info_favor(Long userid,Boolean flag){
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(userid);
        //用户信息返回空
        if(userinfoTable==null)
            return false;
        if(flag) userinfoTable.setFavor(userinfoTable.getFavor()+1);
        else userinfoTable.setFavor(userinfoTable.getFavor()-1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return true;
    }
    public Boolean updata_info_fans(Long userid,Boolean flag){
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(userid);
        if(userinfoTable==null)
            return true;
        if(flag) userinfoTable.setFans(userinfoTable.getFans()+1);
        else userinfoTable.setFans(userinfoTable.getFans()-1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return true;
    }
    public Boolean updata_info(FansTable fansTable,boolean flag){
        //flag为true 表示为关注
        if(flag){
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
}
