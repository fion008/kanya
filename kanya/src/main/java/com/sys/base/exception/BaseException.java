package com.sys.base.exception;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常
 * 
 * @author healy
 * 
 *         定义异常时，需要先确定异常所属模块。例如：添加商户报错 可以定义为 [10020001] 前四位数为系统模块编号，后4位为错误代码 ,唯一 <br>
 *         商户门户异常 1002 <br>
 *         会员门户异常 1004 <br>
 *         boss门户异常 1005 <br>
 *         商户API 异常 1008 <br>
 *         支付网关异常 1009 <br>
 *         会计门户异常 1010 <br>
 *         通知应用异常 1011 <br>
 *         银行服务异常 1012 <br>
 *         银行后置异常 1013 <br>
 *         支付规则异常 1015 <br>
 *         用户服务异常 2002 <br>
 *         boss服务异常 2005 <br>
 *         结算服务异常 2006 <br>
 *         订单服务异常 2007 <br>
 *         账户服务异常 2008 <br>
 *         退款服务异常 2009 <br>
 *         会计服务异常 2010 <br>
 *         通知服务异常 2011 <br>
 *         商户接口异常2012 <br>
 *         证书异常 3001 <br>
 *         风控异常 4001 <br>
 *         计费异常 5001 <br>
 *         成本计费异常 6001 <br>
 *         限制开关异常 7001 <br>
 *         限制开关（业务）异常 7002 <br>
 *         限制开关（金额限制）异常 7003 <br>
 *         银行打款异常 8001 <br>
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BaseException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BaseException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BaseException newInstance(String msgFormat, Object... args) {
		return new BaseException(this.code, msgFormat, args);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message) {
		super(message);
	}
}
