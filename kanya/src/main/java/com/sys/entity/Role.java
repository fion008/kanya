package com.sys.entity;
import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("role")  
public class Role extends BaseEntity{
private static final long serialVersionUID = 1L;  
private Long status;
private String description;
private String name;
public Long getStatus() {return status;}
public void setStatus(Long status) {this.status = status;}
public String getDescription() {return description;}
public void setDescription(String description) {this.description = description;}
public String getName() {return name;}
public void setName(String name) {this.name = name;}
}