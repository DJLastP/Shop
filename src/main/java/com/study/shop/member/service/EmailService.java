package com.study.shop.member.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {
	@Autowired
    private JavaMailSender emailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;
    
    public void sendSimpleMessage(String email, String newPw) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("djlastp12345@gmail.com");
        message.setTo(email);
        message.setSubject("비밀번호초기화");
        message.setText("비밀번호는 : " + newPw);
        emailSender.send(message);
    }
    
    //HTML 메일 보내기
    public void sendHTMLEmail() {
    	MimeMessage message = emailSender.createMimeMessage();

    	String password = "111111";
    	
    	try {
			message.setSubject("임시 제목");
			message.setText(setContext(password), "UTF-8", "html");
			message.addRecipients(MimeMessage.RecipientType.TO, "beiy@naver.com");
			emailSender.send(message);
    	} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    
    //HTML 메일 보낼 시 내용 세팅
    public String setContext(String password) {
    	Context context = new Context();
    	
    	context.setVariable("password", password);
    	
    	return templateEngine.process("mail", context);
    }
    
    
	//랜덤비번 생성 메소드
	public String getRandomPw(int length) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = length;
		Random random = new Random();

		String generatedString = random.ints(leftLimit,rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(targetStringLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		
		return generatedString;
	}
	
    
}