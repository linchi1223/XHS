package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * count对象 userinfo_table
 * 
 * @author zsh
 * @date 2020-11-09
 */
public class UserinfoTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userid;

    /** 粉丝数 */
    @Excel(name = "粉丝数")
    private Long fans;

    /** 关注数 */
    @Excel(name = "关注数")
    private Long favor;

    /** 文章数 */
    @Excel(name = "文章数")
    private Long textCount;

    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setFans(Long fans) 
    {
        this.fans = fans;
    }

    public Long getFans() 
    {
        return fans;
    }
    public void setFavor(Long favor) 
    {
        this.favor = favor;
    }

    public Long getFavor() 
    {
        return favor;
    }
    public void setTextCount(Long textCount) 
    {
        this.textCount = textCount;
    }

    public Long getTextCount() 
    {
        return textCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userid", getUserid())
            .append("fans", getFans())
            .append("favor", getFavor())
            .append("textCount", getTextCount())
            .toString();
    }
}
