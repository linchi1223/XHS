package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户点赞管理对象 favor_table
 * 
 * @author zsh
 * @date 2020-10-22
 */
public class FavorTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞id */
    private Long favorid;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    /** 文章id */
    @Excel(name = "文章id")
    private Long textid;

    public void setFavorid(Long favorid) 
    {
        this.favorid = favorid;
    }

    public Long getFavorid() 
    {
        return favorid;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("favorid", getFavorid())
            .append("userid", getUserid())
            .append("textid", getTextid())
            .toString();
    }
}
