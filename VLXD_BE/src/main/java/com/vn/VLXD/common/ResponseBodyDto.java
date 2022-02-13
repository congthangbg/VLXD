package com.vn.VLXD.common;


import com.vn.VLXD.contants.MessageConstant;

public class ResponseBodyDto<E> {
	
	private String messageCode;
	private String message;
	private long totalRecords;
	private E data;

	public ResponseBodyDto() {
		this.messageCode = MessageConstant.MSG_OK_CODE;
		this.message = MessageConstant.MSG_OK;
		this.totalRecords = 0;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
}
