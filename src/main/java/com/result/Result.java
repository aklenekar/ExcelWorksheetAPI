package com.result;

public class Result<T> {

	private String messsage;
	private int resultCode;
	private T data;

	public Result(String message, ResultCode resultCode) {
		this.setMesssage(message);
		this.setResultCode(resultCode.getCode());
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
