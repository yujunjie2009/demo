package com.newtiming.core.exception;

import com.newtiming.finance.enumType.ErrorCode;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1804330902134311183L;

	//异常信息  
    private String errorCode;
    private String message;
    
    
    public BusinessException(ErrorCode ec){
        super(ec.getDesc());  
        this.errorCode = ec.getValue();
        this.message = ec.getDesc();
    }
    
    public BusinessException(String errorCode){
        super(errorCode);  
        this.errorCode = errorCode;
        this.message = ErrorCode.fromValue(errorCode).getDesc();
    }
  
    
    
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {  
        return message;  
    }  
  
    public void setMessage(String message) {  
        this.message = message;  
    }
}