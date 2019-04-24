package com.result;

public enum ResultCode {

	SUCCESS(200),
	ERROR(500);

	private int code;

	private ResultCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
