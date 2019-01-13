package com.sys.entity;

import com.sys.base.annotation.PrimaryKey;
import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("app_user")
public class App_user extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long identity;
	private String name;
	private String avatar;
	private String openId;
	private String session_key;
	private String phone;

	@PrimaryKey
	// 懒更新中的主键
	// @AutoIncrement //自增字段不参与插入
	// @NotBatchInsert // 不参与批量插入
	public Long getId() {
		return super.getId();
	}

	public Long getIdentity() {
		return identity;
	}

	public void setIdentity(Long identity) {
		this.identity = identity;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}