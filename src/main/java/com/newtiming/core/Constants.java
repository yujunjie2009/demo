package com.newtiming.core;

/**
 * 常量
 * @ClassName Constants
 * @Version V1.0
 */
public final class Constants {

/***********************************************************************************************************************/
	
	//用户名
	public static final String USERNAME_REGEX = "^[A-Za-z0-9_\\-]{6,45}$";
	//MD5加密密码,32位小写
	public static final String PASSWORD_REGEX = "^[a-z0-9]{32}$";

	public static final String SERIOUS_NO_REGEX = "^[a-zA-Z0-9]{32}$";//
	//手机短信验证码
	public static final String PHONE_AUTH_CODE_REGEX = "^[0-9]{6}$";//
	//字母、数字
	public static final String GENERAL_REGEX = "^[A-Za-z0-9]*$";
	//IP
	public static final String IP_REGEX = "^\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b$";
	//日期
	public static final String DATE_REGEX = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
	//UUID
	public static final String UUID_REGEX = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";
	//MAC
	public static final String MAC_REGEX = "^[A-F0-9]{2}([-:][A-F0-9]{2}){5}$";
	//HOUR
	public static final String HOUR_REGEX = "^([1]?[0-9])|2[0-3]$";
	//特殊字符
	public static final String CHARFILTER_REGEX = "^[`~!@#$%^&*()+-=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]$";
	//邮编
	public static final String ZIP_CODE_REGEX = "^[a-zA-Z0-9]{3,12}$";
	//分辨率
	public static final String RESOLUTION_REGEX = "^[1-9]+\\d*\\*[1-9]+\\d*$";
	//IPS
	public static final String IPS_REGEX = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))(,(?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))*$";
	//MACS
	public static final String MACS_REGEX = "^(([a-fA-F0-9]{2}){6})|([a-fA-F0-9]{2}(-[a-fA-F0-9]{2}){5}(,[a-fA-F0-9]{2}(-[a-fA-F0-9]{2}){5}))|(([a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5}(,[a-fA-F0-9]{2}(:[a-fA-F0-9]{2}){5})))*$";
		
	public static final String ORDER_NUMBER_REGEX = "^\\d{13,15}[a-zA-Z0-9]{2}$";//订单编号
	

/***********************************************************************************************************************/

	public static final String LOGIN_USER_INFO = "login_user_info";
	public static final String LOGIN_USER_DEPARTMENT = "login_user_department";
	public static final String LOGIN_USER_ROLE = "login_user_role";
	public static final String LOGIN_USER_PERMISSIONS = "login_user_permissons";
	public static final String DEFAULT_PASSWORD = "12345678";
	public static final String ROLE_ADMIN = "admin";
	public static final String WAITING_CONFIRM_DETAIL_COUNT = "waiting_confirm_detail_count";
	public static final String BALANCE = "balance";

}