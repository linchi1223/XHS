package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章管理对象 text_table
 * 
 * @author zsh
 * @date 2020-10-29
 */
public class TextTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章id */
    private Long textid;

    /** 文章名 */
    @Excel(name = "文章名")
    private String textname;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String textcontent;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 分类号 */
    @Excel(name = "分类号")
    private Long csid;

    /** 上传时间 */
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uptime;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long favor;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    public void setTextid(Long textid) 
    {
        this.textid = textid;
    }

    public Long getTextid() 
    {
        return textid;
    }
    public void setTextname(String textname) 
    {
        this.textname = textname;
    }

    public String getTextname() 
    {
        return textname;
    }
    public void setTextcontent(String textcontent) 
    {
        this.textcontent = textcontent;
    }

    public String getTextcontent() 
    {
        return textcontent;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setCsid(Long csid) 
    {
        this.csid = csid;
    }

    public Long getCsid() 
    {
        return csid;
    }
    public void setUptime(Date uptime) 
    {
        this.uptime = uptime;
    }

    public Date getUptime() 
    {
        return uptime;
    }
    public void setFavor(Long favor) 
    {
        this.favor = favor;
    }

    public Long getFavor() 
    {
        return favor;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("textid", getTextid())
            .append("textname", getTextname())
            .append("textcontent", getTextcontent())
            .append("picture", getPicture())
            .append("csid", getCsid())
            .append("uptime", getUptime())
            .append("favor", getFavor())
            .append("userid", getUserid())
            .toString();
    }
}
