package com.vn.DATN.services;

import javax.mail.MessagingException;
import javax.xml.bind.ValidationException;

import org.springframework.web.multipart.MultipartFile;

import com.vn.DATN.Exception.ValidateException;

public interface MailService {

	String sendMailHepper(String mailReqJson, MultipartFile[] multipartFiles) throws MessagingException, ValidateException;

}
