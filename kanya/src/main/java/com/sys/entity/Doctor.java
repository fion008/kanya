package com.sys.entity;
import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("doctor")  
public class Doctor extends BaseEntity{
private static final long serialVersionUID = 1L;  
private String phone;
private String sex;
private Date updatetime;
private String department;
private String name;
private String profCer;
private String qualCer;
private String major;
private String hospital;
public String getPhone() {return phone;}
public void setPhone(String phone) {this.phone = phone;}
public String getSex() {return sex;}
public void setSex(String sex) {this.sex = sex;}
public Date getUpdatetime() {return updatetime;}
public void setUpdatetime(Date updatetime) {this.updatetime = updatetime;}
public String getDepartment() {return department;}
public void setDepartment(String department) {this.department = department;}
public String getName() {return name;}
public void setName(String name) {this.name = name;}
public String getProfCer() {return profCer;}
public void setProfCer(String profCer) {this.profCer = profCer;}
public String getQualCer() {return qualCer;}
public void setQualCer(String qualCer) {this.qualCer = qualCer;}
public String getMajor() {return major;}
public void setMajor(String major) {this.major = major;}
public String getHospital() {return hospital;}
public void setHospital(String hospital) {this.hospital = hospital;}
}