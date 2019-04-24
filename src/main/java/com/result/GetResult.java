package com.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResult<T> extends Result<T>{

	public GetResult(String message, ResultCode resultCode) {
		super(message, resultCode);
	}

}
