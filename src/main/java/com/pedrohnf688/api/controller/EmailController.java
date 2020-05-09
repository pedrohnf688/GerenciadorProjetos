package com.pedrohnf688.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(path = "/email-send", method = RequestMethod.GET)
	public String sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Hello from Spring Boot Application");
		message.setTo("pedrohnf2014@gmail.com");
		message.setFrom("pedrohnf2014@gmail.com");

		try {
			mailSender.send(message);
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar email.";
		}
	}
}
