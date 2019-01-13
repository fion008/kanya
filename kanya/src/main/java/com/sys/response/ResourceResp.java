package com.sys.response;

import java.util.List;

import com.sys.base.model.BaseModel;

public class ResourceResp extends BaseModel {
	private Long id;
	private String icon;
	private String title;
	private Long pid;
	private String type;
	private String href;
	private String spread;
	List<ResourceResp> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public List<ResourceResp> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceResp> children) {
		this.children = children;
	}

}