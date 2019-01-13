package com.sys.entity;

import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("basetype")
public class Basetype extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Date lasttime;
	private Long status;
	private String name;
	private String type;
	private String shortName;

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}