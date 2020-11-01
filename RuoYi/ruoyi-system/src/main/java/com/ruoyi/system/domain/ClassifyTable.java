package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章标签管理对象 classify_table
 * 
 * @author zsh
 * @date 2020-10-29
 */
public class ClassifyTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类号 */
    private Long csid;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String cname;

    public void setCsid(Long csid) 
    {
        this.csid = csid;
    }

    public Long getCsid() 
    {
        return csid;
    }
    public void setCname(String cname) 
    {
        this.cname = cname;
    }

    public String getCname() 
    {
        return cname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("csid", getCsid())
            .append("cname", getCname())
            .toString();
    }
}
