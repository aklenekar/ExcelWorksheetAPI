package com.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetResult extends Result{

	public GetResult(String message, ResultCode resultCode) {
		super(message, resultCode);
	}

}
