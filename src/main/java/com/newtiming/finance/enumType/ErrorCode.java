package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;


/**
 * 错误码
 * Created by yujunjie on 2017-03-21.
 */
public enum  ErrorCode {
	SYSTEM_ERROR("E00000", "系统异常"),
	PARAMS_ERROR("E00001", "参数校验 失败"),
	FORBIDDEN("P00000", "无权限"),
	
	LOGIN_FAIL("F00001", "登录失败"),
	USER_EXIST("E00002", "用户名已存在"),
	PASSWORD_ERROR("E00003", "密码不正确"),

	ROLE_EXIST("E10001", "角色名已存在"),
	ROLE_USER_USED("E10002", "该角色在使用，不能删除"),
	
	ACCOUNT_DETAIL_CONFIRMED("E20001", "已确认，不能操作"),
	
	ACCOUNT_CONFIRMED("E30001", "已有明细确认，不能操作"),
	
	SMS_SEND_FAIL("E30002", "短信发送失败"),
	EMAIL_SEND_FAIL("E30003", "邮件发送失败")
	;
	
    private String value;
    private String desc;

    private ErrorCode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    

    public static ErrorCode fromValue(String value) {
    	ErrorCode[] arr = values();
        for (ErrorCode r : arr) {
            if (r.getValue() == value ) {
                return r;
            }
        }
        return null;
    }
    
    public static String desc(String value) {
    	ErrorCode[] arr = values();
        for (ErrorCode r : arr) {
            if (r.getValue() == value ) {
                return r.getDesc();
            }
        }
        return null;
    }
    
    public static final Map<String, String> getMap(){
    	ErrorCode[] arr = values();
    	Map<String,String> codeMap = new HashMap<String,String>();
    	for (ErrorCode v : arr) {
    		codeMap.put(v.getValue(), v.getDesc());
        }
    	return codeMap;
    }
}
