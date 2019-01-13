import java.io.IOException;

import org.json.JSONException;

import com.qcloudsms.httpclient.HTTPException;
import com.sys.common.utils.SmsSingleSender;
import com.sys.common.utils.SmsSingleSenderResult;

public class QcloudSmsTest {

	public static void main(String[] args) {
		// 短信应用SDK AppID
		int appid = 1400141389; // 1400开头

		// 短信应用SDK AppKey
		String appkey = "9ff6e135e66bccbab6f3b6da23e415bd";

		// 需要发送短信的手机号码
		String[] phoneNumbers = { "18226196958", "12345678902", "12345678903" };

		// 短信模板ID，需要在短信应用中申请
		// NOTE: 这里的模板ID`7839`只是一个示例，
		// 真实的模板ID需要在短信控制台中申请
		int templateId = 7839;

		// 签名
		// NOTE: 这里的签名"腾讯云"只是一个示例，
		// 真实的签名需要在短信控制台中申请，另外
		// 签名参数使用的是`签名内容`，而不是`签名ID`
		String smsSign = "腾讯云";

		// 单发短信
		try {
			SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult result = ssender.send(0, "86",
					phoneNumbers[0], "您本次的验证码为123456，请于3分钟内填写。如非本人操作，请忽略本短信。", "", "");
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
}
