package com.sys.entity;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("interrupt_type")
public class Interrupt_type extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String icon;
	private String time;
	private Long status;
	private String name;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
}