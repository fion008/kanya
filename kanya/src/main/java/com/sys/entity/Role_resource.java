package com.sys.entity;
import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("role_resource")  
public class Role_resource extends BaseEntity{
private static final long serialVersionUID = 1L;  
private Long resourceId;
private Long roleId;
public Long getResourceId() {return resourceId;}
public void setResourceId(Long resourceId) {this.resourceId = resourceId;}
public Long getRoleId() {return roleId;}
public void setRoleId(Long roleId) {this.roleId = roleId;}
}