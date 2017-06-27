package com.newtiming.finance.enumType;

/**
 * 收支类型
 * Created by yujunjie on 2017-05-24.
 */
public enum  AccountDetailRecordType {
	IN(1, "收入"),
	OUT(2, "支出");

    private Integer value;
    private String desc;

    private AccountDetailRecordType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AccountDetailRecordType fromValue(Integer value) {
        AccountDetailRecordType[] arr = values();
        for (AccountDetailRecordType r : arr) {
            if (r.getValue() == value ) {
                return r;
            }
        }
        return null;
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
