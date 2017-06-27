package com.newtiming.finance.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.common.util.PropertyUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 通过短信接口发送短信
 */
public class SmsUtil {
	public static String serverUrl = "https://eco.taobao.com/router/rest";//短信接口服务器
	public static String appKey = "23380305";//key
	public static String appSecret = "3d97e9efd9c5988145330832fe851212";//密钥
	public static String smsFreeSignName = "新鼎明影视投资";//短信签名
	
	public static TaobaoClient client = null;

	public static String remindCode = "SMS_71305544";//预收支提醒-模板编号
	
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws Exception{
		//从配置文件读取配置信息
		Properties props = PropertyUtil.getProperties("properties/app-config.properties");
		serverUrl = props.getProperty("sms.serverUrl");
		appKey = props.getProperty("sms.appKey");
		appSecret = props.getProperty("sms.appSecret");
		smsFreeSignName = props.getProperty("sms.smsFreeSignName");
		//初始化客户端
		client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
	}
	
	/**
	 * 发送验证码
	 * @param rec_num
	 * @param sms_template_code 短信模板
	 * @param paramString
	 * @return
	 */
	public static boolean sendSms(String rec_num, String sms_template_code, String paramString) {
		boolean success = false;
//		client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
//		TaobaoClient client = new  DefaultTaobaoClient("https://eco.taobao.com/router/rest", "23380305", "3d97e9efd9c5988145330832fe851212");  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("extend");
		req.setSmsType("normal");
		req.setSmsFreeSignName(smsFreeSignName);//签名 
		req.setSmsParamString(paramString);
		req.setRecNum(rec_num);//手机号码
		req.setSmsTemplateCode(sms_template_code);
		
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			JSONObject obj = JSONObject.fromObject(rsp);
			success = (boolean) obj.get("success");
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public static boolean sendSms(String rec_num, String sms_template_code, Map<String,String> paramMap) {
		String paramString = JSONObject.fromObject(paramMap).toString();
		return sendSms(rec_num, sms_template_code, paramString);
	}
	
	public static boolean sendRemindSms(String rec_num, Map<String,String> paramMap) {
		String paramString = JSONObject.fromObject(paramMap).toString();
		return sendSms(rec_num, remindCode, paramString);
	}
	
	/*public static String  test(String userphone,String money,String bankno,String itemname,String smscode){
		String success="false";	
		 AlibabaAliqinFcSmsNumSendRequest req =   new   AlibabaAliqinFcSmsNumSendRequest();  

		 req.setExtend("extend");  

		 req.setSmsType("normal");  

		 req.setSmsFreeSignName(smsFreeSignName);//签名  

		 req.setSmsParamString("{'username':'"+userphone+"','money':'"+money+"',"
										+ "'bankno':'"+bankno+"','itemname':'"+itemname+"'}");//参数

		 req.setRecNum(userphone);//手机号码

		 req.setSmsTemplateCode(smscode);

		 AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody()); 
			JSONObject obj =  JSONObject.fromObject(rsp);
			success=obj.get("success").toString();
		} catch (ApiException e) {
			e.printStackTrace();
		}  

		return success;
	}*/
	public static void main(String[] args) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("itemname", "新鼎明一号基金");
		params.put("recordType", "预支");
		params.put("amount", "10000");
		params.put("expectDatetime", "2017-06-20");
		sendSms("15967146675", remindCode, params);
		
//		String paramString = "{'amount':'10000','name':'新鼎明一号基金','recordType':'支','expectDatetime':'2017-06-20'}";
//		sendSms("15967146675", rdmindCode, paramString);
		
//		test("15967146675", "1000", "4116896578", "新鼎明", "SMS_56665019");
	}
	
}