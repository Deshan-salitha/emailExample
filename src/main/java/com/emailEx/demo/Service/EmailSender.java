package com.emailEx.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendMail(String to,String subject,String text) throws NoSuchProviderException {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("deshanw@mexxar.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

}
