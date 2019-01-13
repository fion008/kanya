package com.sys.entity;

import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("fa_user")
public class Fa_user extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long ysgrade;
	private String ysteam;
	private String ysphnum;
	private Date updatetime;
	private String ysgender;
	private Long app_user_id;
	private String ysgroup;
	private Long ysage;
	private String ysname;
	private Integer status;

	public Long getYsgrade() {
		return ysgrade;
	}

	public void setYsgrade(Long ysgrade) {
		this.ysgrade = ysgrade;
	}

	public String getYsteam() {
		return ysteam;
	}

	public void setYsteam(String ysteam) {
		this.ysteam = ysteam;
	}

	public String getYsphnum() {
		return ysphnum;
	}

	public void setYsphnum(String ysphnum) {
		this.ysphnum = ysphnum;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getYsgender() {
		return ysgender;
	}

	public void setYsgender(String ysgender) {
		this.ysgender = ysgender;
	}

	public Long getApp_user_id() {
		return app_user_id;
	}

	public void setApp_user_id(Long app_user_id) {
		this.app_user_id = app_user_id;
	}

	public String getYsgroup() {
		return ysgroup;
	}

	public void setYsgroup(String ysgroup) {
		this.ysgroup = ysgroup;
	}

	public Long getYsage() {
		return ysage;
	}

	public void setYsage(Long ysage) {
		this.ysage = ysage;
	}

	public String getYsname() {
		return ysname;
	}

	public void setYsname(String ysname) {
		this.ysname = ysname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}