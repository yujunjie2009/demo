package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yujunjie on 2017-04-11.
 */
public enum  AccountDetailStatus {

	WAITING_RECHECK(1, "待复核"),
	WAITING_CONFIRM(2, "待确认"),
	CONFIRMED(3, "已确认");

    private Integer value;
    private String desc;

    private AccountDetailStatus(Integer value, String desc) {
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
    

    public static AccountDetailStatus fromValue(Integer value) {
    	AccountDetailStatus[] arr = values();
        for (AccountDetailStatus r : arr) {
            if (r.getValue() == value ) {
                return r;
            }
        }
        return null;
    }

    public static final int[] getValueArr(){
    	AccountDetailStatus[] arr = values();
    	int[] valueArr = new int[arr.length];
    	for (int i=0; i<arr.length; i++) {
            valueArr[i] = arr[i].getValue();
        }
    	return valueArr;
    }
    
    public static final Map<Integer, String> getMap(){
    	AccountDetailStatus[] arr = values();
    	Map<Integer,String> activityTypeMap = new HashMap<Integer,String>();
    	for (AccountDetailStatus v : arr) {
    		activityTypeMap.put(v.getValue(), v.getDesc());
        }
    	return activityTypeMap;
    }
}
