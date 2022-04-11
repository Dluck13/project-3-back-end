package com.project3.revtech.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;


// this may need to be moved to controller
@Service
@CrossOrigin
public class EmailService {
	

	
	@Autowired
	private JavaMailSender emailSender;
	

	// sends message
	public void sendMessage(String to, String subject, String text) {
		
		// here for testing purposes
	/*	emailSender.setHost("smtp.gmail.com");
	    emailSender.setPort(587);
	    emailSender.setUsername("devdemoacct1@gmail.com");
	    emailSender.setPassword("Gmaildemo-1");
	    
	    Properties props = emailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true") ;
        props.put("mail.smtp.auth", "true") ;
	    
	    
        emailSender.setJavaMailProperties(props);  */
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
}
