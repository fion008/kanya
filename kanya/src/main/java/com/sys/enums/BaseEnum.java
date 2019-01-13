package com.sys.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BaseEnum {
	//支付状态
	NOPAY("NOPAY", "0"), // 未支付
	PAY("PAY", "1"), // 已经支付
	//支付方式 1表示支付宝支付，2表示微信支付，3表示银联支付
	ALIPAY("ALIPAY", "1"), 
	WECHATPAY("WECHATPAY", "2"), 
   //支付路径 1表示线上支付，2表示线下支付
	ONLINE("ONLINE", "1"), 
	OFFLINE("OFFLINE", "2");
	
	
	/** 描述 */
	private String type;
	/** 枚举值 */
	private String value;

	/** 构造函数 */
	private BaseEnum(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static BaseEnum getEnumByValue(String value) {
		BaseEnum resultEnum = null;
		BaseEnum[] enumAry = BaseEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getValue().equalsIgnoreCase(value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static BaseEnum getEnum(String type) {
		BaseEnum resultEnum = null;
		BaseEnum[] enumAry = BaseEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (enumAry[i].getType().equalsIgnoreCase(type)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

	public static Map<String, Map<String, Object>> toMap() {
		BaseEnum[] ary = BaseEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("type", ary[num].getType());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List toList() {
		BaseEnum[] ary = BaseEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", String.valueOf(ary[i].getValue()));
			map.put("type", ary[i].getType());
			list.add(map);
		}
		return list;
	}

	/**
	 * 取枚举的json字符串
	 * 
	 * @return
	 */
	public static String getJsonStr() {
		BaseEnum[] enums = BaseEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (BaseEnum senum : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(senum.toString()).append("',type:'").append(senum.getType())
					.append("',value:'").append(senum.getValue()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}
}
