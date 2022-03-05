package com.vn.VLXD.services.Impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.VLXD.Exception.ValidateException;
import com.vn.VLXD.config.Mail.MailContants;
import com.vn.VLXD.dto.request.MailReq;
import com.vn.VLXD.services.MailService;
@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public String sendMailHepper(String mailReqJson, MultipartFile[] multipartFiles)throws MessagingException, ValidateException {
		try {
			
			ObjectMapper objectMapper  = new ObjectMapper();
			MailReq mailReq = objectMapper.readValue(mailReqJson, MailReq.class);
			
			String array[] = mailReq.getLstTo().toArray(new String[0]);
			MimeMessage message = javaMailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(array);
			helper.setSubject(mailReq.getSubject());
			helper.setText(mailReq.getContents());
			
			if(multipartFiles != null) {
				for (MultipartFile muFile : multipartFiles) {
					String fileName = StringUtils.cleanPath(muFile.getOriginalFilename());
					helper.addAttachment(fileName, muFile);
				}
			}
			
			
			javaMailSender.send(message);
			
			return "Gửi mail thành công ";
			
		} catch (MailAuthenticationException e) {
			throw new ValidateException(MailContants.MESSAGE,"020");
		}
		catch (Exception e) {
			throw new ValidateException("Gửi mail không thành công","010");
		}
	}
}
