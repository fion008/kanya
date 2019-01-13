package com.sys.base.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.sys.base.annotation.AutoIncrement;
import com.sys.base.annotation.NotBatchInsert;
import com.sys.base.annotation.PrimaryKey;
import com.sys.base.annotation.Table;
import com.sys.base.dao.BaseDao;
import com.sys.base.exception.BaseException;
import com.sys.base.model.BaseEntity;
import com.sys.base.tool.ConvertUtil;
import com.sys.common.CustomParameter;
import com.sys.common.PageBean;
import com.sys.common.PageParam;
import com.sys.common.PagingRounds;

public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements
		BaseDao<T> {
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	private static int DEFAULT_MAX_BATCH_SIZE = 64;// 批量插入时 一次最多插入条数
	private static final Object[] EMPTY_PARAM = new Object[] {};
	private static final Class<?>[] OBJECT_CLASS = new Class<?>[] { Object.class };

	private String tableName(Class<? extends BaseEntity> c) {// 找表名
		Table t = c.getAnnotation(Table.class);
		if (t == null) {
			throw new BaseException("table not specified");
			// 实体类上未声明所属表
		}
		return t.value();
	}

	private String methodToField(String methodName) {
		methodName = methodName.substring(3);
		if (methodName.length() >= 2) {
			return methodName.substring(0, 1).toLowerCase()
					+ methodName.substring(1);
		}
		return methodName.toLowerCase();
	}

	@Override
	public int insert(T baseModel) {
		Assert.isInstanceOf(BaseEntity.class, baseModel);
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) baseModel
				.getClass();

		String tableName = tableName(c);
		HashMap<String, Object> params = new HashMap<String, Object>();
		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<Object> modelValues = new ArrayList<Object>();

		Method[] ms = c.getMethods();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				Object val;
				try {
					val = m.invoke(baseModel, EMPTY_PARAM);
					if (val == null) {
						continue;
					}
				} catch (Exception e) {
					throw new BaseException("invoke failed", e);
				}
				AutoIncrement ai = m.getAnnotation(AutoIncrement.class);
				if (ai != null) {
					throw new BaseException(
							"AutoIncrement field should be null when insert");
					// 插入数据时，自增字段应该为空
				}
				modelFields.add(methodToField(mName));
				modelValues.add(val);
			}
		}
		if (modelFields.size() == 0) {
			throw new BaseException("Empty model");
			// 实体类所有字段都为空，插入数据无意义
		}
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("modelValues", modelValues);
		return getSqlSession().insert("insertBaseModelByMap", params);
	}

	@Override
	public int insert(List<T> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		T bm = list.get(0);
		Assert.isInstanceOf(BaseEntity.class, bm);

		int listSize = list.size();
		if (listSize > DEFAULT_MAX_BATCH_SIZE) {
			List<T> listA, listB;
			if (listSize / 2 > DEFAULT_MAX_BATCH_SIZE) {// 二叉树形结构，限制调用层数，防止batchSize过小。
				listA = list.subList(0, listSize / 2);
				listB = list.subList(listSize / 2, listSize);
			} else {
				listA = list.subList(0, DEFAULT_MAX_BATCH_SIZE);
				listB = list.subList(DEFAULT_MAX_BATCH_SIZE, listSize);
			}
			list = null;
			return insert(listA) + insert(listB);
		}

		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) bm
				.getClass();

		String tableName = tableName(c);
		HashMap<String, Object> params = new HashMap<String, Object>();

		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<ArrayList<Object>> listValues = new ArrayList<ArrayList<Object>>(
				listSize);

		Method[] ms = c.getMethods();
		ArrayList<Method> msList = new ArrayList<Method>();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				NotBatchInsert nbi = m.getAnnotation(NotBatchInsert.class);
				if (nbi == null) {
					modelFields.add(methodToField(mName));
					msList.add(m);
				}
			}
		}

		int size = modelFields.size();
		if (size == 0) {
			throw new BaseException("Empty batch model");
		}

		for (T t : list) {
			ArrayList<Object> modelValues = new ArrayList<Object>(size);
			for (Method m : msList) {
				Object val;
				try {
					val = m.invoke(t, EMPTY_PARAM);
				} catch (Exception e) {
					throw new BaseException("invoke failed", e);
				}
				modelValues.add(val);
			}
			listValues.add(modelValues);
		}
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("listValues", listValues);
		return getSqlSession().insert("batchInsertBaseModelByMap", params);
	}

	@Override
	public int update(T baseModel) {
		Assert.isInstanceOf(BaseEntity.class, baseModel);
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) baseModel
				.getClass();

		String tableName = tableName(c);
		HashMap<String, Object> params = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> modelEntries = new ArrayList<HashMap<String, Object>>();
		String keyField = null;
		Object keyValue = null;
		Method[] ms = c.getMethods();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				if (mName.equalsIgnoreCase("getCreatetime")
						|| mName.equalsIgnoreCase("getLasttime")) {
					continue;
				}
				Object val;
				try {
					val = m.invoke(baseModel, EMPTY_PARAM);
				} catch (Exception e) {
					throw new BaseException("invoke failed", e);
				}
				PrimaryKey ai = m.getAnnotation(PrimaryKey.class);
				if (ai == null) {// 普通 字段
					HashMap<String, Object> e = new HashMap<String, Object>();
					e.put("key", methodToField(mName));
					e.put("value", val);
					modelEntries.add(e);
				} else {
					if (keyField != null) {
						throw new BaseException("Duplicated keyField");// 主键重复设置，最多只能设一个
					}
					keyField = methodToField(mName);
					keyValue = val;
				}
			}
		}
		if (keyField == null) {
			throw new BaseException("key field not found");
			// 主键未声明
		}
		if (keyValue == null) {
			throw new BaseException("key value should not be null");
			// 主键对应的值为空
		}
		params.put("tableName", tableName);
		params.put("keyField", keyField);
		params.put("keyValue", keyValue);
		params.put("modelEntries", modelEntries);
		return getSqlSession().update("updateBaseModelByKey", params);
	}

	@Override
	public T getByKey(Object kv, Class<T> clazz) {
		if (kv == null) {
			throw new BaseException("key value should not be null");
		}
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) clazz;
		String tableName = tableName(c);
		Method[] ms = c.getMethods();
		String keyField = null;
		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<Class<?>> returnClass = new ArrayList<Class<?>>();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				String fieldName = methodToField(mName);
				PrimaryKey ai = m.getAnnotation(PrimaryKey.class);
				if (ai != null) {
					if (keyField != null) {
						throw new BaseException("Duplicated keyField");// 主键重复设置，最多只能设一个
					}
					keyField = fieldName;
				}
				modelFields.add(fieldName);
				returnClass.add(m.getReturnType());
			}
		}
		if (keyField == null) {
			throw new BaseException("key field not found");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("keyField", keyField);
		params.put("keyValue", kv);

		return getSqlSession().selectOne("selectBaseModelByKey",new CustomParameter(params, false, c));
	}

	/**
	 * 根据联合条件，查询一个对象 使用时需确保联合条件的惟一性
	 * 
	 * @param filter
	 *            联合条件
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	protected T getByFilter(Map<String, Object> filter, Class<T> clazz) {
		if (filter == null || filter.size() == 0) {
			throw new BaseException("key value should not be null");
		}
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) clazz;
		String tableName = tableName(c);
		Method[] ms = c.getMethods();
		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<Class<?>> returnClass = new ArrayList<Class<?>>();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				String fieldName = methodToField(mName);
				modelFields.add(fieldName);
				returnClass.add(m.getReturnType());
			}
		}
		Iterator<String> itK = filter.keySet().iterator();
		ArrayList<HashMap<String, Object>> filterList = new ArrayList<HashMap<String, Object>>();
		while (itK.hasNext()) {
			String filterField = itK.next();
			if (!modelFields.contains(filterField)) {
				throw new BaseException("filter field [" + filterField
						+ "] not found");
			}
			HashMap<String, Object> kv = new HashMap<String, Object>();
			kv.put("key", filterField);
			kv.put("value", filter.get(filterField));
			filterList.add(kv);
		}

		// 重复的代码
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("filterEntries", filterList);

		HashMap<String, Object> mapResult = getSqlSession().selectOne(
				"selectBaseModelByFilter", params);
		if (mapResult == null) {// 未找到值
			return null;
		}
		T bm;
		try {
			bm = clazz.newInstance();
		} catch (Exception e) {
			throw new BaseException("init model failed", e);
		}
		for (int i = 0; i < modelFields.size(); i++) {// 手动组装类的数据
			String field = modelFields.get(i);
			Object val = mapResult.get(field);
			Class<?> rClass = returnClass.get(i);
			String mName = "set" + field.substring(0, 1).toUpperCase()
					+ field.substring(1);
			String convertName = ConvertUtil.TYPE_MAPPING.get(rClass.getName());
			if (convertName == null) {
				throw new BaseException(
						"Mapping not configured, please add in ConvertUtil. "
								+ rClass.getName());
			}
			try {
				Object realObj = ConvertUtil.class.getMethod(convertName,
						OBJECT_CLASS).invoke(null, new Object[] { val });
				c.getMethod(mName, new Class[] { rClass }).invoke(bm,
						new Object[] { realObj });
			} catch (Exception e) {
				throw new BaseException("invoke model failed", e);
			}
		}
		return bm; // 重复的代码
	}

	/**
	 * 根据唯一性字段的值，查询一个对象
	 * 
	 * @param valueOfUnique
	 *            主键的值
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	protected List<T> getEntityList(Object valueOfField, String field,
			Class<T> clazz) {

		if (valueOfField == null) {
			throw new BaseException("key value should not be null");
		}
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) clazz;
		String tableName = tableName(c);
		Method[] ms = c.getMethods();
		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<Class<?>> returnClass = new ArrayList<Class<?>>();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				String fieldName = methodToField(mName);
				modelFields.add(fieldName);
				returnClass.add(m.getReturnType());
			}
		}
		if (!modelFields.contains(field)) {
			throw new BaseException("field not found");
		}

		// 重复的代码
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("keyField", field);
		params.put("keyValue", valueOfField);
		return getSqlSession().selectList("selectBaseModelByKey",
				new CustomParameter(params, false, c));
	}

	/**
	 * 根据唯一性字段的值，查询一个对象
	 * 
	 * @param valueOfUnique
	 *            主键的值
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	protected T getByUnique(Object valueOfUnique, String uniqueField,
			Class<T> clazz) {

		if (valueOfUnique == null) {
			throw new BaseException("key value should not be null");
		}
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) clazz;
		String tableName = tableName(c);
		Method[] ms = c.getMethods();
		ArrayList<String> modelFields = new ArrayList<String>();
		ArrayList<Class<?>> returnClass = new ArrayList<Class<?>>();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				String fieldName = methodToField(mName);
				modelFields.add(fieldName);
				returnClass.add(m.getReturnType());
			}
		}
		if (!modelFields.contains(uniqueField)) {
			throw new BaseException("unique field not found");
		}

		// 重复的代码
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("modelFields", modelFields);
		params.put("keyField", uniqueField);
		params.put("keyValue", valueOfUnique);
		return getSqlSession().selectOne("selectBaseModelByKey",
				new CustomParameter(params, false, c));
	}

	@Override
	public int deleteByKey(Object kv, Class<T> clazz) {
		if (kv == null) {
			throw new BaseException("key value should not be null");
		}
		@SuppressWarnings("unchecked")
		Class<? extends BaseEntity> c = (Class<? extends BaseEntity>) clazz;

		String keyField = null;
		Method[] ms = c.getMethods();
		for (Method m : ms) {// 注意自增字段的验证
			String mName = m.getName();
			if (mName.startsWith("get") && (!mName.equals("getClass"))) {
				String fieldName = methodToField(mName);
				PrimaryKey ai = m.getAnnotation(PrimaryKey.class);
				if (ai != null) {
					if (keyField != null) {
						throw new BaseException("Duplicated keyField");// 主键重复设置，最多只能设一个
					}
					keyField = fieldName;
				}
			}
		}
		if (keyField == null) {
			throw new BaseException("key field not found");
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName(c));
		params.put("keyField", keyField);
		params.put("keyValue", kv);
		return getSqlSession().delete("deleteBaseModelByKey", params);
	}

	/**
	 * 不分页，查全部
	 * 
	 * @param paramMap
	 * @param sqlId
	 * @return
	 */
	protected <E> List<E> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		return getSqlSession().selectList(sqlId, paramMap);
	}

	/**
	 * 根据分页参数 查询list
	 * 
	 * @param pageParam
	 * @param paramMap
	 * @param sqlId
	 * @return
	 */
	protected <E> List<E> listBy(PageParam pageParam,
			Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		List<E> l = getSqlSession().selectList(sqlId,paramMap,new PagingRounds((pageParam.getPageNum() - 1)* pageParam.getNumPerPage(), pageParam.getNumPerPage()));
		return l;
	}

	/**
	 * 分页，查部分。 一条sql，自动分析list获取count
	 * 
	 * @param pageParam
	 * @param paramMap
	 * @param sqlId
	 * @return
	 */
	protected PageBean listPage(PageParam pageParam,Map<String, Object> paramMap, String sqlId) {

		if (paramMap == null)
			paramMap = new HashMap<String, Object>();
		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession().selectList(sqlId,paramMap,new PagingRounds((pageParam.getPageNum() - 1)* pageParam.getNumPerPage(), pageParam.getNumPerPage()));
		// 统计总记录数
		Number countObject = (Number) getSqlSession().selectOne(sqlId,
				new CustomParameter(paramMap, true, Long.class));
		Long count = Long.valueOf(countObject.longValue());
		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(),count.intValue(), list);
	}

	/**
	 * 分页，查部分， 需使用两条sql 格式为 1.xxx 2 xxxCount 因为部分复杂sql可简化count
	 * 
	 * @param pageParam
	 * @param paramMap
	 * @param sqlId
	 * @return
	 */
	protected PageBean listPage(Map<String, Object> paramMap,
			PageParam pageParam, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		// 获取分页数据集 , 注切勿换成 sessionTemplate 对象
		List<Object> list = getSqlSession()
				.selectList(
						sqlId,
						paramMap,
						new PagingRounds((pageParam.getPageNum() - 1)
								* pageParam.getNumPerPage(), pageParam
								.getNumPerPage()));
		// 统计总记录数
		Number countObject = (Number) getSqlSession().selectOne(
				sqlId + "Count", paramMap);
		Long count = Long.valueOf(countObject.longValue());
		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(),
				count.intValue(), list);
	}

	
	/**
	 * 改变状态
	 * @param tableName
	 * @param field
	 * @param value
	 * @return
	 */
	public int  changeStatus(String tableName,String field,String value,String keyField,String keyValue){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("field", field);
		params.put("value", value);
		params.put("keyField", keyField);
		params.put("keyValue", keyValue);
		return getSqlSession().delete("changeBaseModelByKey", params);
	}
	
	public Date findDBTime() {
		return getSqlSession().selectOne("findDbTime");
	}

}