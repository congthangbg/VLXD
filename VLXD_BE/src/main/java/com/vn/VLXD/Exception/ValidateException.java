package com.vn.VLXD.Exception;

import org.springframework.aop.AopInvocationException;

public class ValidateException extends AopInvocationException{

	public ValidateException(String msg,String errCode) {
		super(msg);
	}
	
}
