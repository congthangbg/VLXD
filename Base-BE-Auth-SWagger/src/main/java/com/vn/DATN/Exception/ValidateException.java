package com.vn.DATN.Exception;

import javax.xml.bind.ValidationException;

public class ValidateException extends ValidationException{

	public ValidateException(String msg,String errCode) {
		super(msg,errCode);
	}
	
}
