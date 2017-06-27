package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;


/**
 * 收支提醒方式
 * Created by yujunjie on 2017-06-15.
 */
public enum  RemindType {
	SMS(1, "短信"),
    EMAIL(2, "邮件");

    private Integer value;
    private String desc;

    private RemindType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    

    public static RemindType fromValue(String value) {
    	RemindType[] arr = values();
        for (RemindType r : arr) {
            if (value.equals(r.getValue())) {
                return r;
            }
        }
        return null;
    }

    public static final Integer[] getValueArr(){
    	RemindType[] arr = values();
    	Integer[] valueArr = new Integer[arr.length];
    	for (int i=0; i<arr.length; i++) {
            valueArr[i] = arr[i].getValue();
        }
    	return valueArr;
    }
    
    public static final Map<Integer, String> getMap(){
    	RemindType[] arr = values();
    	Map<Integer,String> activityTypeMap = new HashMap<Integer,String>();
    	for (RemindType v : arr) {
    		activityTypeMap.put(v.getValue(), v.getDesc());
        }
    	return activityTypeMap;
    }
}
