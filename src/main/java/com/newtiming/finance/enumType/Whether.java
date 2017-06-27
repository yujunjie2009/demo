package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yujunjie on 2017-04-11.
 */
public enum  Whether {
	YES(1, "是"),
    NO(0, "否");

    private Integer value;
    private String desc;

    private Whether(Integer value, String desc) {
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
    

    public static Whether fromValue(Integer value) {
    	Whether[] arr = values();
        for (Whether r : arr) {
            if (r.getValue() == value ) {
                return r;
            }
        }
        return null;
    }

    public static final int[] getValueArr(){
    	Whether[] arr = values();
    	int[] valueArr = new int[arr.length];
    	for (int i=0; i<arr.length; i++) {
            valueArr[i] = arr[i].getValue();
        }
    	return valueArr;
    }
    
    public static final Map<Integer, String> getMap(){
    	Whether[] arr = values();
    	Map<Integer,String> activityTypeMap = new HashMap<Integer,String>();
    	for (Whether v : arr) {
    		activityTypeMap.put(v.getValue(), v.getDesc());
        }
    	return activityTypeMap;
    }
}
