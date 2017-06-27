package com.newtiming.finance.util;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.common.util.PropertyUtil;

/**
 * 邮件工具
 */
public class EmailUtil {
	private static String SERVER="smtp.exmail.qq.com";// 发件服务器
	private static String PORT = "25";//端口
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//
	private static String SSL_PORT = "465";//ssl端口
	
	private static String NAME="新鼎明影视投资";// 发送者,显示的发件人名字
	private static String USER="nt@newtiming.net";// 发送者邮箱地址
	private static String PASSWORD="Newtiming7663";// 密码
	private static String ENCODING="UTF-8";// 邮件编码
	private static EmailUtil emailUtil = null;
	private Session session;
	
	public static final String TEMPLATE_DEFAULT = "<html>"
			+"<head><base target=\"_blank\">"
			+"<style type=\"text/css\">"
			+"::-webkit-scrollbar{ display: none; }"
			+"body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}"
			+"td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}"
			+"pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}"
			+"th,td{font-family:arial,verdana,sans-serif;line-height:1.666}"
			+"img{ border:0}"
			+"header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}"
			+"a,td a{color:#064977}"
			+"</style>"
			+"<style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>"
			+"</head>"
			+"<body>"
			+"$htmlContent$"
			+"</body></html>";
	
	static {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws Exception{
		Properties props = PropertyUtil.getProperties("properties/app-config.properties");
    	
		SERVER = props.getProperty("mail.server");
		NAME = props.getProperty("mail.name");
		USER = props.getProperty("mail.user");
		PASSWORD = props.getProperty("mail.password");
		ENCODING = props.getProperty("mail.encoding");
	}
	
	public EmailUtil() {
		final Properties props = new Properties();
		props.put("mail.smtp.host", SERVER);// SMTP服务器地址
		props.put("mail.smtp.auth", Boolean.TRUE.toString());// SMTP服务器是否需要用户认证，默认为false
		props.put("mail.stmp.timeout", "2000");
//		props.put("mail.smtp.port", PORT);// 端口
		
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", SSL_PORT);
		props.setProperty("mail.smtp.socketFactory.port", SSL_PORT);
		
		session = Session.getDefaultInstance(props, new Authenticator() {
			// 验证帐户
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASSWORD);
			}
		});
	}

	/**
	 * 单例模式
	 * @return
	 */
	public static EmailUtil getInstance() {
		if(emailUtil == null){
			emailUtil = new EmailUtil();
		}
		return emailUtil;
	}


	
	/**
	 * 
	 * @param title
	 * @param htmlContent
	 * @param toUsers
	 */
	public void sendemail(String title, String htmlContent, Map<String,String> toUsers){
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(USER, NAME, ENCODING));

			// 添加多个目的用户
			Set<String> set = toUsers.keySet();
			for(String email : set){
				String user = toUsers.get(email);
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, user, ENCODING));
			}
			
			msg.setSubject(title, ENCODING);
			MimeBodyPart content = new MimeBodyPart();
			content.setContent(htmlContent, "text/html;charset=UTF-8");// 设置html内容
			MimeMultipart msgMultipart = new MimeMultipart("mixed");// 设置邮件内容格式为混合内容
			msgMultipart.addBodyPart(content);
			msg.setContent(msgMultipart);
			Transport.send(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
/*======================================================================================================*/
	
	public static void main(String args[]) throws UnsupportedEncodingException {
		String ss = "<html>"
		+"<head><base target=\"_blank\">"
		+"<style type=\"text/css\">"
		+"::-webkit-scrollbar{ display: none; }"
		+"body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}"
		+"td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}"
		+"pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}"
		+"th,td{font-family:arial,verdana,sans-serif;line-height:1.666}"
		+"img{ border:0}"
		+"header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}"
		+"a,td a{color:#064977}"
		+"</style>"
		+"<style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>"
		+"</head>"
		+"<body>"
		+"$htmlContent$"
		+"</body></html>";
		String htmlContent = ss.replace("$htmlContent$", "某年某月某日的收款提醒");
		
		
		
		String contentBody = "您好！您关注的“${itemname}”，${recordType}金额${amount}元，将于${expectDatetime}到达预期。请及时进行操作。";
		Map<String,String> params = new HashMap<String,String>();
		params.put("itemname", "我的一号基金");
		params.put("recordType", "预支");
		params.put("amount", "1300.00");
		params.put("expectDatetime", "2017-07-30");
		Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			
			contentBody = contentBody.replace("${"+entry.getKey()+"}", entry.getValue());
		}  
		
		String title = "收支提醒";
		
		Map<String,String> toUsers = new HashMap<String,String>();
		toUsers.put("923185078@qq.com", "云中鱼");
		EmailUtil.getInstance().sendemail(title, contentBody, toUsers);
		
		
	}
}

