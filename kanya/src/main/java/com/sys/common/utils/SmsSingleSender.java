package com.sys.common.utils;

import com.qcloudsms.httpclient.DefaultHTTPClient;
import com.qcloudsms.httpclient.HTTPClient;
import com.qcloudsms.httpclient.HTTPException;
import com.qcloudsms.httpclient.HTTPMethod;
import com.qcloudsms.httpclient.HTTPRequest;
import com.qcloudsms.httpclient.HTTPResponse;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 腾讯云短信
 * @author dell
 *
 */
public class SmsSingleSender extends SmsBase {

	private String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms";

	public SmsSingleSender(int appid, String appkey) {
		super(appid, appkey, new DefaultHTTPClient());
	}

	public SmsSingleSender(int appid, String appkey, HTTPClient httpclient) {
		super(appid, appkey, httpclient);
	}

	// 短信应用SDK AppID
	private static int appid = 1400141389; // 1400开头

	// 短信应用SDK AppKey
	private static String appkey = "9ff6e135e66bccbab6f3b6da23e415bd";
	private static String nationCode = "86";// 中国
	private static int templateId = 193877;// 模板编号
	private static String smsSign = "六线速云"; // 签名

	/**
	 * 发送验证码--byliu
	 * 
	 * @param phone
	 * @param code
	 */
	public static void sendMySingleCode(String phone, String code) {
		// 单发短信
	   String smsTemp = "【六线速云】您本次的验证码为"+code+"，请于3分钟内填写。如非本人操作，请忽略本短信。"; // 短信内容
		try {
			SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult result = ssender.send(0, "86", phone,
					smsTemp, "", "");
			System.out.print(result);
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}

	}

	/**
	 * 指定模板短信
	 * 
	 * @param phone
	 * @param code
	 */
	public static void sendMyMuBanCode(String phoneNumber, String code) {
		// 您本次的验证码为{1}，请于{2}分钟内填写。如非本人操作，请忽略本短信。
		try {
			SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			ArrayList<String> params = new ArrayList<String>();
			params.add("code");
			params.add("3");
			SmsSingleSenderResult result = ssender.sendWithParam(nationCode,
					phoneNumber, templateId, params, smsSign, "", "");
			System.out.print(result);
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}

	}

	/**
	 * 普通单发
	 * 
	 * 普通单发短信接口，明确指定内容，如果有多个签名，请在内容中以【】的方式添加到信息内容中，否则系统将使用默认签名
	 * 
	 * @param type
	 *            短信类型，0 为普通短信，1 营销短信
	 * @param nationCode
	 *            国家码，如 86 为中国
	 * @param phoneNumber
	 *            不带国家码的手机号
	 * @param msg
	 *            信息内容，必须与申请的模板格式一致，否则将返回错误
	 * @param extend
	 *            扩展码，可填空
	 * @param ext
	 *            服务端原样返回的参数，可填空
	 * @return {@link}SmsSingleSenderResult
	 * @throws HTTPException
	 *             http status exception
	 * @throws JSONException
	 *             json parse exception
	 * @throws IOException
	 *             network problem
	 */
	public SmsSingleSenderResult send(int type, String nationCode,
			String phoneNumber, String msg, String extend, String ext)
			throws HTTPException, JSONException, IOException {

		long random = SmsSenderUtil.getRandom();
		long now = SmsSenderUtil.getCurrentTime();
		JSONObject body = new JSONObject()
				.put("tel",
						(new JSONObject()).put("nationcode", nationCode).put(
								"mobile", phoneNumber))
				.put("type", type)
				.put("msg", msg)
				.put("sig",
						SmsSenderUtil.calculateSignature(this.appkey, random,
								now, phoneNumber)).put("time", now)
				.put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
				.put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

		HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
				.addHeader("Conetent-Type", "application/json")
				.addQueryParameter("sdkappid", this.appid)
				.addQueryParameter("random", random)
				.setConnectionTimeout(60 * 1000).setRequestTimeout(60 * 1000)
				.setBody(body.toString());

		// TODO Handle timeout exception
		try {
			// May throw IOException and URISyntaxexception
			HTTPResponse res = httpclient.fetch(req);

			// May throw HTTPException
			handleError(res);

			// May throw JSONException
			return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
		} catch (URISyntaxException e) {
			throw new RuntimeException(
					"API url has been modified, current url: " + url);
		}
	}

	/**
	 * 指定模板单发
	 * 
	 * @param nationCode
	 *            国家码，如 86 为中国
	 * @param phoneNumber
	 *            不带国家码的手机号
	 * @param templateId
	 *            信息内容
	 * @param params
	 *            模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param sign
	 *            签名，如果填空，系统会使用默认签名
	 * @param extend
	 *            扩展码，可填空
	 * @param ext
	 *            服务端原样返回的参数，可填空
	 * @return {@link}SmsSingleSenderResult
	 * @throws HTTPException
	 *             http status exception
	 * @throws JSONException
	 *             json parse exception
	 * @throws IOException
	 *             network problem
	 */
	public SmsSingleSenderResult sendWithParam(String nationCode,
			String phoneNumber, int templateId, ArrayList<String> params,
			String sign, String extend, String ext) throws HTTPException,
			JSONException, IOException {

		long random = SmsSenderUtil.getRandom();
		long now = SmsSenderUtil.getCurrentTime();

		JSONObject body = new JSONObject()
				.put("tel",
						(new JSONObject()).put("nationcode", nationCode).put(
								"mobile", phoneNumber))
				.put("sig",
						SmsSenderUtil.calculateSignature(appkey, random, now,
								phoneNumber)).put("tpl_id", templateId)
				.put("params", params).put("sign", sign).put("time", now)
				.put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
				.put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

		HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
				.addHeader("Conetent-Type", "application/json")
				.addQueryParameter("sdkappid", this.appid)
				.addQueryParameter("random", random)
				.setConnectionTimeout(60 * 1000).setRequestTimeout(60 * 1000)
				.setBody(body.toString());

		try {
			// May throw IOException and URISyntaxexception
			HTTPResponse res = httpclient.fetch(req);

			// May throw HTTPException
			handleError(res);

			// May throw JSONException
			return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
		} catch (URISyntaxException e) {
			throw new RuntimeException(
					"API url has been modified, current url: " + url);
		}
	}

	public SmsSingleSenderResult sendWithParam(String nationCode,
			String phoneNumber, int templateId, String[] params, String sign,
			String extend, String ext) throws HTTPException, JSONException,
			IOException {

		return sendWithParam(nationCode, phoneNumber, templateId,
				new ArrayList<String>(Arrays.asList(params)), sign, extend, ext);
	}
}
