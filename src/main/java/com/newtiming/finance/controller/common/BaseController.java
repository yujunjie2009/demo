package com.newtiming.finance.controller.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import com.common.interceptor.SimpleControllerInterceptor;
import com.google.gson.Gson;
import com.newtiming.core.Constants;
import com.newtiming.finance.enumType.AccountType;
import com.newtiming.finance.enumType.ErrorCode;
import com.newtiming.finance.enumType.Permission;

public class BaseController {
    
	protected  final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME); 

	protected  final String adminViewsRoot = "admin";
	protected  final String webViewsRoot = "web";
	
    public  Map<String, Object> getResult(String result, String message){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        resultMap.put("message", message);
        return resultMap;
    }
    
    public  Map<String, Object> fail(String message){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "FAIL");
        resultMap.put("message", message);
        return resultMap;
    }
    
    public  Map<String, Object> fail(String code, String message){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "FAIL");
        resultMap.put("code", code);
        resultMap.put("message", message);
        return resultMap;
    }
    
    
    public  Map<String, Object> failCode(String code){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "FAIL");
        resultMap.put("code", code);
        resultMap.put("message", ErrorCode.desc(code));
        return resultMap;
    }
    

    public  Map<String, Object> failCode(ErrorCode code){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "FAIL");
        resultMap.put("code", code.getValue());
        resultMap.put("message", code.getDesc());
        return resultMap;
    }
    
    public  Map<String, Object> OK(){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "OK");
        resultMap.put("code", "00000");
        return resultMap;
    }
    
    public  Map<String, Object> OK(Object data){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "OK");
        resultMap.put("code", "00000");
        resultMap.put("data", data);
        return resultMap;
    }

    public Gson gson = new Gson();
    public ObjectMapper objMapper = new ObjectMapper();
    
    /**
     * 从参数校验器中取出转义后的参数
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	protected Map<String, Object> getRequestParams(HttpServletRequest request){
    	Map<String,Object> paramMap = (Map<String, Object>)request.getAttribute(SimpleControllerInterceptor.REQUEST_PARAMS);
    	if(paramMap == null){
    		System.out.println("注意：参数校验器未能获取参数，请确认该接口是否需要校验参数");
    		paramMap = new HashMap<String,Object>();
    	}
    	return paramMap;
    	
    }
    
    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
//        InetAddress.getLocalHost().getHostAddress()
        return ip;
    }
    
    @SuppressWarnings("unchecked")
	public boolean hasPermission(String permissionCode, HttpServletRequest request){
    	List<String> permissions = (List<String>)request.getSession().getAttribute(Constants.LOGIN_USER_PERMISSIONS);
    	if(permissions.contains(permissionCode)){
    		return true;
    	}else{
    		return false;
    	}
    }
    

    
    @SuppressWarnings("unchecked")
	public List<Integer> getQueryAccountTypeList(HttpServletRequest request){
    	/*Role role = (Role)request.getSession().getAttribute(Constants.LOGIN_USER_ROLE);
    	if(role.getId().equals(Long.valueOf(0))){
    		
    	}*/
    	List<String> permissions = (List<String>)request.getSession().getAttribute(Constants.LOGIN_USER_PERMISSIONS);
    	List<Integer> accountTypeList = new ArrayList<Integer>();
    	if(permissions.contains(Permission.FUND_LOOK.getValue())){
    		accountTypeList.add(AccountType.FUND.getValue());
    	}
    	if(permissions.contains(Permission.PROJECT_LOOK.getValue())){
    		accountTypeList.add(AccountType.PROJECT1.getValue());
    		accountTypeList.add(AccountType.PROJECT2.getValue());
    	}
    	if(permissions.contains(Permission.BANK_LOOK.getValue())){
    		accountTypeList.add(AccountType.BANK.getValue());
    	}
    	if(permissions.contains(Permission.OTHER_LOOK.getValue())){
    		accountTypeList.add(AccountType.OTHER.getValue());
    	}
    	
    	return accountTypeList;
    }
    

    @SuppressWarnings("unchecked")
	public List<Integer> getRecheckAccountTypeList(HttpServletRequest request){
    	List<String> permissions = (List<String>)request.getSession().getAttribute(Constants.LOGIN_USER_PERMISSIONS);
    	List<Integer> accountTypeList = new ArrayList<Integer>();
    	if(permissions.contains(Permission.FUND_RECHECK.getValue())){
    		accountTypeList.add(AccountType.FUND.getValue());
    	}
    	if(permissions.contains(Permission.PROJECT_RECHECK.getValue())){
    		accountTypeList.add(AccountType.PROJECT1.getValue());
    		accountTypeList.add(AccountType.PROJECT2.getValue());
    	}
    	if(permissions.contains(Permission.BANK_RECHECK.getValue())){
    		accountTypeList.add(AccountType.BANK.getValue());
    	}
    	if(permissions.contains(Permission.OTHER_RECHECK.getValue())){
    		accountTypeList.add(AccountType.OTHER.getValue());
    	}
    	
    	return accountTypeList;
    }
    
   
    
    /**
     * 验证数据权限
     * @param accountTypeEnum
     * @param accountType
     * @param detailType
     * @return
     */
    public boolean detailPermission(AccountType accountTypeEnum, Integer accountType, Integer detailType){
    	if(!accountTypeEnum.getValue().equals(accountType)){
			return false;
		}
		
		if(!accountType.equals(detailType/100)){//操作的数据是否符合账目类型
			return false;
		}
		return true;
    }
    
    /**
     * 返回错误页面
     * @param request
     * @param message
     * @return
     */
    protected String error(HttpServletRequest request, String message) /*throws UnsupportedEncodingException*/{
    	request.setAttribute("message", message);
		return "/error/warn";
    }
    /**
     * 重定向到错误接口
     * @param message
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String error(String message) throws UnsupportedEncodingException{
    	return "redirect:/error/warn.htm?message="+URLEncoder.encode(message, "utf-8");
    }
    
    
}
