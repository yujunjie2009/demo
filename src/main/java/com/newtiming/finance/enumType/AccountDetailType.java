package com.newtiming.finance.enumType;

import java.util.HashMap;
import java.util.Map;

/**
 * 收支明细类型
 * Created by yujunjie on 2017-05-24.
 */
public enum  AccountDetailType {
	/**************** [项目I] begin ********************/
	//入款
	PROJECT1_IN_OTHER(121, "其他(项)"),
	PROJECT1_IN_REFUND_PRINCIPAL(122, "回款本金"),
	PROJECT1_IN_REFUND_INTEREST(123, "回款利息"),
	//出款
	PROJECT1_OUT_INVEST(111, "项目投资(I)"),
	PROJECT1_OUT_OTHER(112, "其他(项)"),
	/**************** [项目I] end **********************/
	
	/***************** [项目II] begin ******************/
	//入款
	PROJECT2_IN_SALES(211, "销售收入"),
	PROJECT2_IN_OTHER(212, "其他(项)"),
	//出款
	PROJECT2_OUT_INVEST(221, "项目投资(II)"),
	PROJECT2_OUT_MADE_FEE(222, "制作费"),
	PROJECT2_OUT_OTHER(223, "其他(项)"),
	/***************** [项目II] end ********************/

	/**************** [基金] begin *********************/
	//入款
	FUND_IN_RAISE_PRINCIPAL(311, "募集本金"),
	FUND_IN_SALES_CHARGE(312, "认购费"),
	FUND_IN_OTHER(313, "其他(基)"),
	//出款
	FUND_OUT_SALES_CHARGE(321, "认购费"),
	FUND_OUT_TRUST_FEE(322, "托管费"),
	FUND_OUT_MANAGE_FEE(323, "管理费"),
	FUND_OUT_OTHER(324, "其他(基)"),
	FUND_OUT_CASH_PRINCIPAL(325, "兑付本金"),
	FUND_OUT_CASH_INTEREST(326, "兑付利息"),
	/**************** [基金] end ************************/
	
	/***************** [银行借贷] begin *****************/
	//入款
	BANK_IN_INVEST(411, "贷款金额"),
	BANK_IN_OTHER(412, "其他(银)"),
	//出款
	BANK_OUT_REFUND_PRINCIPAL(421, "还款本金(银)"),
	BANK_OUT_REFUND_INTEREST(422, "还款利息(银)"),
	BANK_OUT_OTHER(423, "其他(银)"),
	/***************** [银行借贷] end ********************/
	
	/***************** [其他融资] begin ******************/
	//入款
	OTHER_IN_FINANCING(511, "融资金额"),
	OTHER_IN_OTHER(512, "其他"),
	//出款
	OTHER_OUT_REFUND_PRINCIPAL(521, "还款本金"),
	OTHER_OUT_REFUND_INTEREST(522, "还款利息"),
	OTHER_OUT_OTHER(523, "其他");
	/***************** [其他融资] begin ******************/

    private Integer value;
    private String desc;

    private AccountDetailType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static AccountDetailType fromValue(Integer value) {
        AccountDetailType[] arr = values();
        for (AccountDetailType r : arr) {
            if (r.getValue().equals(value)) {
                return r;
            }
        }
        return null;
    }

    public static final Map<Integer, String> getMapByAccountType(int accountType, int recordType){
    	AccountDetailType[] arr = values();
    	Map<Integer,String> resultMap = new HashMap<Integer,String>();
    	for (AccountDetailType v : arr) {
    		if(v.getValue()/10 == accountType*10+recordType){
    			resultMap.put(v.getValue(), v.getDesc());
    		}
        }
    	return resultMap;
    }
    
    public static final AccountDetailType getByAccountType(int accountType, int recordType, String desc){
    	AccountDetailType[] arr = values();
    	for (AccountDetailType v : arr) {
    		if(v.getValue()/10 == accountType*10+recordType){
    			if(desc.equals(v.getDesc())){
    				return v;
    			}
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
