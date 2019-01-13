import com.aliyuncs.exceptions.ClientException;
import com.sys.common.utils.SendCodeUtil;

/**
 * 阿里云短信测试
 * 
 * @author liu
 * 
 */
public class aliyunSendSms {

	public static void mian() {
		try {
			SendCodeUtil.sendSms("18226196958", "用戶", "123456");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
