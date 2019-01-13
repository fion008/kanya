package com.sys.entity;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("cure_plan")
public class Cure_plan extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String eventIds;
	private String name;
	private String photo;
	private Integer status;

	public String getEventIds() {
		return eventIds;
	}

	public void setEventIds(String eventIds) {
		this.eventIds = eventIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}