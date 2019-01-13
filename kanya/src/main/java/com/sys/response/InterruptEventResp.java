package com.sys.response;

import java.util.Date;

import com.sys.base.model.BaseModel;

public class InterruptEventResp extends BaseModel {
	private Long id;
	private String content;
	private Integer status;
	private Date end_time;
	private String name;
	private String length;
	private Date start_time;
	private Long type;
	private Date expected_end_time;
	private Long patientsId;
	private Date createtime;

	private String icon;
	private String typeName;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Date getExpected_end_time() {
		return expected_end_time;
	}

	public void setExpected_end_time(Date expected_end_time) {
		this.expected_end_time = expected_end_time;
	}

	public Long getPatientsId() {
		return patientsId;
	}

	public void setPatientsId(Long patientsId) {
		this.patientsId = patientsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}