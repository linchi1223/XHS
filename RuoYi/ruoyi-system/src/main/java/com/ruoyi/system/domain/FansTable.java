package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * fans对象 fans_table
 * 
 * @author ruoyi
 * @date 2020-11-11
 */
public class FansTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id号 */
    private Long fansid;

    /** 关注id */
    @Excel(name = "关注id")
    private Long userid1;

    /** 被关注id */
    @Excel(name = "被关注id")
    private Long userid2;

    public void setFansid(Long fansid) 
    {
        this.fansid = fansid;
    }

    public Long getFansid() 
    {
        return fansid;
    }
    public void setUserid1(Long userid1) 
    {
        this.userid1 = userid1;
    }

    public Long getUserid1() 
    {
        return userid1;
    }
    public void setUserid2(Long userid2) 
    {
        this.userid2 = userid2;
    }

    public Long getUserid2() 
    {
        return userid2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fansid", getFansid())
            .append("userid1", getUserid1())
            .append("userid2", getUserid2())
            .toString();
    }
}
