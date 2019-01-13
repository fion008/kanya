package com.sys.entity;
import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("user")  
public class User extends BaseEntity{
private static final long serialVersionUID = 1L;  
private String phone;
private Date updatetime;
private Long status;
private String name;
private String login_name;
private String password;
private Long roleId;
public String getPhone() {return phone;}
public void setPhone(String phone) {this.phone = phone;}
public Date getUpdatetime() {return updatetime;}
public void setUpdatetime(Date updatetime) {this.updatetime = updatetime;}
public Long getStatus() {return status;}
public void setStatus(Long status) {this.status = status;}
public String getName() {return name;}
public void setName(String name) {this.name = name;}
public String getLogin_name() {return login_name;}
public void setLogin_name(String login_name) {this.login_name = login_name;}
public String getPassword() {return password;}
public void setPassword(String password) {this.password = password;}
public Long getRoleId() {return roleId;}
public void setRoleId(Long roleId) {this.roleId = roleId;}
}