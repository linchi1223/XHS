package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IUserTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 普通用户管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-30
 */
@Service
public class UserTableServiceImpl implements IUserTableService 
{
    @Autowired
    private UserTableMapper userTableMapper;
    @Autowired
    private FavorTableMapper favorTableMapper;
    @Autowired
    private CollectTableMapper collectTableMapper;
    @Autowired
    private TextTableMapper textTableMapper;
    @Autowired
    private CommentTableMapper commentTableMapper;
    @Autowired
    private FansTableMapper fansTableMapper;
    @Autowired
    private UserinfoTableMapper userinfoTableMapper;
    /**
     * 查询普通用户管理
     * 
     * @param userid 普通用户管理ID
     * @return 普通用户管理
     */
    @Override
    public UserTable selectUserTableById(Long userid)
    {
        return userTableMapper.selectUserTableById(userid);
    }

    /**
     * 查询普通用户管理列表
     * 
     * @param userTable 普通用户管理
     * @return 普通用户管理
     */
    @Override
    public List<UserTable> selectUserTableList(UserTable userTable)
    {
        return userTableMapper.selectUserTableList(userTable);
    }

    /**
     * 新增普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    @Override
    public int insertUserTable(UserTable userTable)
    {
        return userTableMapper.insertUserTable(userTable);
    }

    /**
     * 修改普通用户管理
     * 
     * @param userTable 普通用户管理
     * @return 结果
     */
    @Override
    public int updateUserTable(UserTable userTable)
    {
        return userTableMapper.updateUserTable(userTable);
    }

    /**
     * 删除普通用户管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserTableByIds(String ids)
    {
        updata_info(Long.parseLong(ids));
        return userTableMapper.deleteUserTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除普通用户管理信息
     * 
     * @param userid 普通用户管理ID
     * @return 结果
     */
    @Override
    public int deleteUserTableById(Long userid)
    {
        updata_info(userid);
        return userTableMapper.deleteUserTableById(userid);
    }

    /**
     *
     * 根据用户名称获取用户
     *
     * @param phone 需要查询的用户名称
     * @return 结果
     */
    public UserTable selectUserTableByUserPhone(String phone){
        return userTableMapper.selectUserTableByUserPhone(phone);
    }

    public void updata_info(Long userid){
        //通过用户id删除用户的收藏
        delect_collectByUserid(userid);
        //优先删除用户的点赞
        delect_favor(userid);
        //删除用户的平评论
        delect_comment(userid);
        //删除用户的关注
        delect_fans1(userid);
        //删除关注用户的信息
        delect_fans2(userid);
        //删除用户的文章
        delect_text(userid);
        //删除用户的粉丝数，关注数，文章数信息
        userinfoTableMapper.deleteUserinfoTableById(userid);
    }
    /*
     * 通过用户id删除收藏
     * */
    public void delect_collectByUserid(Long userid) {
        CollectTable collectTable = new CollectTable();
        List<CollectTable> collectTables= collectTableMapper.selectCollectTableList(collectTable);
        for (int i=0;i<collectTables.size();++i)
            if(userid==collectTables.get(i).getUserid())
                collectTableMapper.deleteCollectTableById(collectTables.get(i).getCollid());
    }
    /*
    * 通过用户id删除用户的点赞信息
    * */
    public void delect_favor(Long userid) {
        List<FavorTable> favorTables = favorTableMapper.selectFavorTableByUserid(userid);
        for (int i = 0; i < favorTables.size(); ++i)
            favorTableMapper.deleteFavorTableById(favorTables.get(i).getFavorid());
    }
    /*
    * 通过用户id删除用户的文章
    * */
    public void delect_text(Long userid){
            // 删除用户的文章
            TextTable textTable = new TextTable();
            List<TextTable> textTable1 = textTableMapper.selectTextTableList(textTable);
            for(int i = 0;i<textTable1.size();++i)
                if(textTable1.get(i).getUserid()==userid){
                    textTableMapper.deleteTextTableById(textTable1.get(i).getTextid());
                }
    }
    /*
     * 通过用户id删除评论
     * */
    public void delect_comment(Long userid){
        //通过用户id 删除用户的评论
        List<CommentTable>  commentTables= commentTableMapper.selectCommentTableByUserId(userid);
        for(int i = 0;i<commentTables.size();++i)
            commentTableMapper.deleteCommentTableById(commentTables.get(i).getCommentid());
    }
    /*
    * 通过用户id删除关注的用户
    * */
    public void delect_fans1(Long userid){
        //删除用户时自动取关所有关注的人
        //通过用户id 删除用户的评论
        List<FansTable> fansTables = fansTableMapper.selectFansTableByUserid(userid);
        for(int i = 0;i<fansTables.size();++i)
            fansTableMapper.deleteFansTableById(fansTables.get(i).getFansid());
    }
    /*
     * 删除用户被关注的信息
     * */
    public void delect_fans2(Long userid){
        //删除用户时 删除所有和其有关的关注信息
        FansTable fansTable = new FansTable();
        List<FansTable> fansTables = fansTableMapper.selectFansTableList(fansTable);
        for(int i = 0;i<fansTables.size();++i)
            if(fansTables.get(i).getUserid2()==userid)
                fansTableMapper.deleteFansTableById(fansTables.get(i).getFansid());
    }
}
