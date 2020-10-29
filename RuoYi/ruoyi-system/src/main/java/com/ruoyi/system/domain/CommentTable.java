package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户评论管理对象 comment_table
 * 
 * @author zsh
 * @date 2020-10-22
 */
public class CommentTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long commentid;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    /** 文章id */
    @Excel(name = "文章id")
    private Long textid;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String comment;

    /** 评论时间 */
    @Excel(name = "评论时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    public void setCommentid(Long commentid) 
    {
        this.commentid = commentid;
    }

    public Long getCommentid() 
    {
        return commentid;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setTextid(Long textid) 
    {
        this.textid = textid;
    }

    public Long getTextid() 
    {
        return textid;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentid", getCommentid())
            .append("userid", getUserid())
            .append("textid", getTextid())
            .append("comment", getComment())
            .append("time", getTime())
            .toString();
    }
}
