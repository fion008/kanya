package com.sys.entity;
import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("event_remind")  
public class Event_remind extends BaseEntity{
private static final long serialVersionUID = 1L;  
private String content;
private Date time;
private Long status;
private Long add_user;
public String getContent() {return content;}
public void setContent(String content) {this.content = content;}
public Date getTime() {return time;}
public void setTime(Date time) {this.time = time;}
public Long getStatus() {return status;}
public void setStatus(Long status) {this.status = status;}
public Long getAdd_user() {return add_user;}
public void setAdd_user(Long add_user) {this.add_user = add_user;}
}