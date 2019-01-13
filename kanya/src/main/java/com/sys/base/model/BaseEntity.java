package com.sys.base.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sys.base.annotation.AutoIncrement;
import com.sys.base.annotation.NotBatchInsert;
import com.sys.base.annotation.PrimaryKey;

/**
 * 
 * @描述: 基础实体类，包含各实体公用属性 .
 * @作者:  .
 * @创建时间: 2013-7-28,下午8:53:52 .
 * @版本: 1.0 .
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date createtime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
	@PrimaryKey //懒更新中的主键
	@AutoIncrement //自增字段不参与插入
	@NotBatchInsert // 不参与批量插入
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotBatchInsert // 不参与批量插入
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
