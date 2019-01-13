package com.qcloudsms.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

	/**
	 * 
	 * @param url
	 * @param jsonObj
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject Post(String url, JSONObject jsonObj)
			throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonObj.toString(), "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		// 输出调用结果
		if (response != null && response.getStatusLine().getStatusCode() == 200) {
			// 生成 JSON 对象
			JSONObject obj = JSONObject.parseObject(result);
			return obj;
		}
		return null;

	}
}
