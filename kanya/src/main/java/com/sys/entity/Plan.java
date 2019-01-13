package com.sys.entity;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;

@Table("plan")
public class Plan extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long patientsId;
	private Long current_step;
	private Long current_day;
	private String start_time;
	private String end_time;
	private Long day;
	private Long step;
	private Long cure_plan;

	public Long getPatientsId() {
		return patientsId;
	}

	public void setPatientsId(Long patientsId) {
		this.patientsId = patientsId;
	}

	public Long getCurrent_step() {
		return current_step;
	}

	public void setCurrent_step(Long current_step) {
		this.current_step = current_step;
	}

	public Long getCurrent_day() {
		return current_day;
	}

	public void setCurrent_day(Long current_day) {
		this.current_day = current_day;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Long getCure_plan() {
		return cure_plan;
	}

	public void setCure_plan(Long cure_plan) {
		this.cure_plan = cure_plan;
	}

}