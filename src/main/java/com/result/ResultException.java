package com.result;

public class ResultException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final String message;
    private final int httpStatus;
    
	public ResultException(String errMsg, ResultCode reultCode) {
		super(errMsg);
		this.message = errMsg;
		this.httpStatus = reultCode.getCode();
	}
	
	@Override
    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

}
