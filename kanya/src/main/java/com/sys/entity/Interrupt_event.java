package com.sys.entity;

import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("interrupt_event")
public class Interrupt_event extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String content;
	private Integer status;
	private Date end_time;
	private String name;
	private String length;
	private Date start_time;
	private Long type;
	private Date expected_end_time;
	private Long patientsId;
	private Integer isRemind;

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

	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}

}