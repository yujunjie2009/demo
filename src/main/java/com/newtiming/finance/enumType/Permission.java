package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;
import com.newtiming.finance.constant.PermissionCode;


/**
 * Created by yujunjie on 2017-06-01.
 */
public enum  Permission {

	FUND_EDIT(PermissionCode.FUND_EDIT, "编辑权限"),
	FUND_RECHECK(PermissionCode.FUND_RECHECK, "复核权限"),
	FUND_CONFIRM(PermissionCode.FUND_CONFIRM, "确认权限"),
	FUND_LOOK(PermissionCode.FUND_LOOK, "查看权限"),
	
	PROJECT_EDIT(PermissionCode.PROJECT_EDIT, "编辑权限"),
	PROJECT_RECHECK(PermissionCode.PROJECT_RECHECK, "复核权限"),
	PROJECT_CONFIRM(PermissionCode.PROJECT_CONFIRM, "确认权限"),
	PROJECT_LOOK(PermissionCode.PROJECT_LOOK, "查看权限"),
	
	BANK_EDIT(PermissionCode.BANK_EDIT, "编辑权限"),
	BANK_RECHECK(PermissionCode.BANK_RECHECK, "复核权限"),
	BANK_CONFIRM(PermissionCode.BANK_CONFIRM, "确认权限"),
	BANK_LOOK(PermissionCode.BANK_LOOK, "查看权限"),
	
	OTHER_EDIT(PermissionCode.OTHER_EDIT, "编辑权限"),
	OTHER_RECHECK(PermissionCode.OTHER_RECHECK, "复核权限"),
	OTHER_CONFIRM(PermissionCode.OTHER_CONFIRM, "确认权限"),
	OTHER_LOOK(PermissionCode.OTHER_LOOK, "查看权限"),
	
	ACCOUNT_PLAN(PermissionCode.ACCOUNT_PLAN, "资金规划权限");

    private String value;
    private String desc;

    private Permission(String value, String desc) {
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
    

    public static Permission fromValue(String value) {
    	Permission[] arr = values();
        for (Permission r : arr) {
            if (value.equals(r.getValue())) {
                return r;
            }
        }
        return null;
    }

    public static final String[] getValueArr(){
    	Permission[] arr = values();
    	String[] valueArr = new String[arr.length];
    	for (int i=0; i<arr.length; i++) {
            valueArr[i] = arr[i].getValue();
        }
    	return valueArr;
    }
    
    public static final Map<String, String> getMap(){
    	Permission[] arr = values();
    	Map<String,String> activityTypeMap = new HashMap<String,String>();
    	for (Permission v : arr) {
    		activityTypeMap.put(v.getValue(), v.getDesc());
        }
    	return activityTypeMap;
    }
}
