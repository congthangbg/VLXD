package com.vn.DATN.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailReq {
	//@Email
	//private String from
	//private String password
	private String subject;
	private String contents;
	private List<String> lstTo;
}
