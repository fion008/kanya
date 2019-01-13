package com.sys.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sys.base.model.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleResourceResp extends BaseModel {
	private Long id;
	private String name;
	private Boolean checked;
	List<RoleResourceResp> children;

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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<RoleResourceResp> getChildren() {
		return children;
	}

	public void setChildren(List<RoleResourceResp> children) {
		this.children = children;
	}

}