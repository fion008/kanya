package com.sys.entity;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("patients")
public class Patients extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long doctorId;
	private Long app_user_id;
	private Long age;
	private String name;
	private String avatar;
	private Integer status;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getApp_user_id() {
		return app_user_id;
	}

	public void setApp_user_id(Long app_user_id) {
		this.app_user_id = app_user_id;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}