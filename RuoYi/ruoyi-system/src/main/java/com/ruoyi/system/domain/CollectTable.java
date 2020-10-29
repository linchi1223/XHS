package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户收藏管理对象 collect_table
 * 
 * @author zsh
 * @date 2020-10-22
 */
public class CollectTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收藏id */
    private Long collid;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    /** 文章id */
    @Excel(name = "文章id")
    private Long textid;

    public void setCollid(Long collid) 
    {
        this.collid = collid;
    }

    public Long getCollid() 
    {
        return collid;
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
            .append("collid", getCollid())
            .append("userid", getUserid())
            .append("textid", getTextid())
            .toString();
    }
}
