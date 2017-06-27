package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;

/**
 * 账目类型
 * Created by yujunjie on 2017-05-24.
 */
public enum  AccountType {
	PROJECT1(1, "项目投资I类"),
	PROJECT2(2, "项目投资II类"),
	FUND(3, "基金类"),
	BANK(4, "银行借贷"),
	OTHER(5, "其他融资");

    private Integer value;
    private String desc;

    private AccountType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AccountType fromValue(Integer value) {
        AccountType[] arr = values();
        for (AccountType r : arr) {
            if (r.getValue() == value ) {
                return r;
            }
        }
        return null;
    }

    public static final Map<Integer, String> getMap(){
    	AccountType[] arr = values();
    	Map<Integer,String> resultMap = new HashMap<Integer,String>();
    	for (AccountType v : arr) {
    		resultMap.put(v.getValue(), v.getDesc());
        }
    	return resultMap;
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
}
