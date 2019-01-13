package com.sys.entity;
import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("history_plan")  
public class History_plan extends BaseEntity{
private static final long serialVersionUID = 1L;  
private Date end_time;
private Long patientsId;
private Long cumulative_days;
private Date start_time;
private Long cumulative_hours;
private String step;
public Date getEnd_time() {return end_time;}
public void setEnd_time(Date end_time) {this.end_time = end_time;}
public Long getPatientsId() {return patientsId;}
public void setPatientsId(Long patientsId) {this.patientsId = patientsId;}
public Long getCumulative_days() {return cumulative_days;}
public void setCumulative_days(Long cumulative_days) {this.cumulative_days = cumulative_days;}
public Date getStart_time() {return start_time;}
public void setStart_time(Date start_time) {this.start_time = start_time;}
public Long getCumulative_hours() {return cumulative_hours;}
public void setCumulative_hours(Long cumulative_hours) {this.cumulative_hours = cumulative_hours;}
public String getStep() {return step;}
public void setStep(String step) {this.step = step;}
}