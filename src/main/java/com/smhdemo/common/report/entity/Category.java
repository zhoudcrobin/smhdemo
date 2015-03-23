/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * 报表分类
 * 
 * <ul>
 * <li>id:分类编号</li>
 * <li>name:名称</li>
 * <li>remark:备注</li>
 * <li>texts:报表记录集</li>
 * <li>charts:图表记录集</li>
 * </ul>
 * 
 * @author zhoudongchu
 */
@Entity
@Table(name = "report_category")
@SequenceGenerator(name = "seq_report_category", sequenceName = "seq_report_category_id", allocationSize = 1)
@JSONType(ignores = { "textReports","chartReports" }) 
public class Category implements Serializable {

    private static final long serialVersionUID = 6590119941274234278L;
    
	@Id
    @GeneratedValue(generator = "seq_report_category",strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
    @Column(name = "remark",columnDefinition = "text")
    private String remark;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, targetEntity = Text.class)
    @JoinTable(name = "report_category_text", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "text_id", referencedColumnName = "id"))
    @OrderBy("id")
    private Set<Text> textReports = new LinkedHashSet<Text>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, targetEntity = Chart.class)
    @JoinTable(name = "report_category_chart", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "chart_id", referencedColumnName = "id"))
    @OrderBy("id")
    private Set<Chart> chartReports = new LinkedHashSet<Chart>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

  

    public Set<Text> getTextReports() {
		return textReports;
	}

	public void setTextReports(Set<Text> textReports) {
		this.textReports = textReports;
	}



	public Set<Chart> getChartReports() {
		return chartReports;
	}

	public void setChartReports(Set<Chart> chartReports) {
		this.chartReports = chartReports;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
