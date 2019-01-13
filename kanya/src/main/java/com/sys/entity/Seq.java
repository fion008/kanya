package com.sys.entity;
import java.util.Date;

import com.sys.base.annotation.Table;
import com.sys.base.model.BaseEntity;
@Table("seq")  
public class Seq extends BaseEntity{
private static final long serialVersionUID = 1L;  
private Long increment;
private Long currentvalue;
private Date lasttime;
private String seqName;
public Long getIncrement() {return increment;}
public void setIncrement(Long increment) {this.increment = increment;}
public Long getCurrentvalue() {return currentvalue;}
public void setCurrentvalue(Long currentvalue) {this.currentvalue = currentvalue;}
public Date getLasttime() {return lasttime;}
public void setLasttime(Date lasttime) {this.lasttime = lasttime;}
public String getSeqName() {return seqName;}
public void setSeqName(String seqName) {this.seqName = seqName;}
}