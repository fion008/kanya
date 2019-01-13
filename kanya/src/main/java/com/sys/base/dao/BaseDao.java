package com.sys.base.dao;

import java.util.Date;
import java.util.List;

public interface BaseDao<T> {

	/**
	 * 增加一个对象，自增字段需为空
	 * 
	 * @param model
	 * @return
	 */
	int insert(T baseModel);

	/**
	 * 批量增加一个对象，自增字段需为空
	 * 
	 * @exception 注意空字段会当成null插入
	 *                ，此时若表中有非空限制会出错
	 * @param model
	 * @return
	 */
	int insert(List<T> list);

	/**
	 * 根据主键，更新一个对象
	 * 
	 * @param model
	 * @return
	 */
	int update(T baseModel);

	/**
	 * 根据主键的值，查询一个对象
	 * 
	 * @param valueOfKey
	 *            主键的值
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	T getByKey(Object valueOfKey, Class<T> clazz);

	/**
	 * 根据主键的值，删除一个对象
	 * 
	 * 慎用
	 * 
	 * @param valueOfKey
	 *            -- 主键的值
	 * @param clazz
	 *            -- 对象类型
	 * @return
	 */
	int deleteByKey(Object valueOfKey, Class<T> clazz);

	/**
	 * 查找DB时间
	 * 
	 * @return
	 */
	Date findDBTime();

	/**
	 * 改变状态
	 * 
	 * @param tableName
	 * @param field
	 * @param value
	 * @param keyField
	 * @param keyValue
	 * @return
	 */
	int changeStatus(String tableName, String field, String value,
			String keyField, String keyValue);

}
