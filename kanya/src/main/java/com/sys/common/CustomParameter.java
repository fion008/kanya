package com.sys.common;

/**
 * 求count时参数
 * @author andy
 *
 */
public class CustomParameter {
	private Object parameter;
	private Class<?> returnClazz;
	private boolean isCount;
	
	public CustomParameter(Object parameter, boolean isCount, Class<?> returnClazz) {
		this.parameter = parameter;
		this.isCount = isCount;
		this.returnClazz = returnClazz;
	}

	public boolean isCount() {
		return isCount;
	}

	public Object getParameter() {
		return parameter;
	}

	public Class<?> getReturnClazz() {
		return returnClazz;
	}

}
