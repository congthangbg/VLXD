package com.vn.VLXD.dto;

public class MessageResponse {
    private String message;
    private String messageCode;

    public MessageResponse(String message,String messageCode) {
        this.message = message;
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
    
    
}
