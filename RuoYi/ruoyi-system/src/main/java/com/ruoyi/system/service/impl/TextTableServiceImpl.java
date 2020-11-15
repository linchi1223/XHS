package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.ITextTableService;
import com.ruoyi.common.core.text.Convert;

import javax.xml.soap.Text;

/**
 * 文章管理Service业务层处理
 * 
 * @author zsh
 * @date 2020-10-29
 */
@Service
public class TextTableServiceImpl implements ITextTableService 
{
    @Autowired
    private TextTableMapper textTableMapper;
    @Autowired
    private UserinfoTableMapper userinfoTableMapper;
    @Autowired
    private CollectTableMapper collectTableMapper;
    @Autowired
    private FavorTableMapper favorTableMapper;
    @Autowired
    private CommentTableMapper commentTableMapper;

    /**
     * 查询文章管理
     * 
     * @param textid 文章管理ID
     * @return 文章管理
     */
    @Override
    public TextTable selectTextTableById(Long textid)
    {
        return textTableMapper.selectTextTableById(textid);
    }

    /**
     * 查询文章管理列表
     * 
     * @param textTable 文章管理
     * @return 文章管理
     */
    @Override
    public List<TextTable> selectTextTableList(TextTable textTable)
    {
        return textTableMapper.selectTextTableList(textTable);
    }

    /**
     * 新增文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    @Override
    public int insertTextTable(TextTable textTable)
    {
        if(textTable.getUptime()==null)
            textTable.setUptime(new Date());
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(textTable.getUserid());
        userinfoTable.setTextCount(userinfoTable.getTextCount()+1);
        userinfoTableMapper.updateUserinfoTable(userinfoTable);
        return textTableMapper.insertTextTable(textTable);
    }

    /**
     * 修改文章管理
     * 
     * @param textTable 文章管理
     * @return 结果
     */
    @Override
    public int updateTextTable(TextTable textTable)
    {
        return textTableMapper.updateTextTable(textTable);
    }

    /**
     * 删除文章管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTextTableByIds(String ids)
    {
        updata_info(Long.parseLong(ids));
        TextTable textTable = textTableMapper.selectTextTableById(Long.parseLong(ids));
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(textTable.getUserid());
        if(userinfoTable!=null) {
            userinfoTable.setTextCount(userinfoTable.getTextCount() - 1);
            userinfoTableMapper.updateUserinfoTable(userinfoTable);
        }
        return textTableMapper.deleteTextTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章管理信息
     * 
     * @param textid 文章管理ID
     * @return 结果
     */
    @Override
    public int deleteTextTableById(Long textid)
    {
        updata_info(textid);
        TextTable textTable = textTableMapper.selectTextTableById(textid);
        UserinfoTable userinfoTable = userinfoTableMapper.selectUserinfoTableById(textTable.getUserid());
        if(userinfoTable!=null) {
            userinfoTable.setTextCount(userinfoTable.getTextCount() - 1);
            userinfoTableMapper.updateUserinfoTable(userinfoTable);
        }
        return textTableMapper.deleteTextTableById(textid);
    }
    /**
     * 查询文章管理
     *
     * @param userid 文章管理ID
     * @return 文章管理
     */
    public List<TextTable> selectTextTableByUserId(Long userid){
        return textTableMapper.selectTextTableByUserId(userid);
    }
    /*
     * 删除文章前删除 点赞和收藏信息
     */
    public void updata_info(Long textid){
        //通过文章id删除文章的收藏信息
        delect_collectByTextid(textid);
        //通过文章id删除文章的点赞信息
        delect_favorByTextid(textid);
        //通过文章id删除对应文章的评论信息
        delect_commentByTextid(textid);
    }
    /*
     * 通过文章id删除收藏
     * */
    public void delect_collectByTextid(Long textid) {
        CollectTable collectTable = new CollectTable();
        List<CollectTable> collectTables= collectTableMapper.selectCollectTableList(collectTable);
        for (int i=0;i<collectTables.size();++i)
            if(textid==collectTables.get(i).getTextid())
                collectTableMapper.deleteCollectTableById(collectTables.get(i).getCollid());
    }
    /*
     * 通过文章id删除点赞
     * */
    public void delect_favorByTextid(Long textid){
        List<FavorTable> favorTables= favorTableMapper.selectFavorTableByTextId(textid);
        for (int i=0;i<favorTables.size();++i)
            favorTableMapper.deleteFavorTableById(favorTables.get(i).getFavorid());
    }
    /*
     * 通过文章id删除评论
     * */
    public void delect_commentByTextid(Long textid){
        List<CommentTable> commentTables= commentTableMapper.selectCommentTableByTextId(textid);
        for (int i=0;i<commentTables.size();++i)
            commentTableMapper.deleteCommentTableById(commentTables.get(i).getCommentid());
    }
}
