package com.sys.entity;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("resource")
public class Resource extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String icon;
	private String open_mode;
	private Long sort;
	private Long status;
	private String description;
	private String name;
	private Long pid;
	private String type;
	private String url;
	private String spread;
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOpen_mode() {
		return open_mode;
	}

	public void setOpen_mode(String open_mode) {
		this.open_mode = open_mode;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}
	
	
}