package com.vn.VLXD.controller;

import javax.mail.MessagingException;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vn.VLXD.Exception.ValidateException;
import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.services.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "EMAIL")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@ApiOperation(value = "Gửi mail")
	@PostMapping(value = "/send",consumes = {"multipart/form-data"})
	public ResponseBodyDto<Object> sendSimpleEMail
	(@RequestParam String jsonMailReq,@RequestParam(required = false) MultipartFile[] multipartFiles) throws MessagingException, ValidationException{
		ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
		dto.setData(mailService.sendMailHepper(jsonMailReq, multipartFiles));
		
		return dto;
	}

}
