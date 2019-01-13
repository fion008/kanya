package com.sys.response;

import com.sys.base.model.BaseModel;

public class PlanResp extends BaseModel {
	private Long id;
	private Long patientsId;
	private Long current_step;
	private Long current_day;
	private String start_time;
	private String end_time;
	private Long day;
	private Long step;
	private Long curePlanId;
	private String curePlanName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientsId() {
		return patientsId;
	}

	public void setPatientsId(Long patientsId) {
		this.patientsId = patientsId;
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

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
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

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	public Long getCurePlanId() {
		return curePlanId;
	}

	public void setCurePlanId(Long curePlanId) {
		this.curePlanId = curePlanId;
	}

	public String getCurePlanName() {
		return curePlanName;
	}

	public void setCurePlanName(String curePlanName) {
		this.curePlanName = curePlanName;
	}

}