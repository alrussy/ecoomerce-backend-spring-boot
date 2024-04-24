package com.alrussy.idantityservice.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {

	//private final JavaMailSender javaMailSender;
	
	
//	public void send(String email, String otp) {
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setTo(email);
//		message.setFrom("alrusswe1@gmail.com");
//		message.setSubject("Verifaiction Code");
//		message.setText(otp);
//		 javaMailSender.send(message);
//	}
}
