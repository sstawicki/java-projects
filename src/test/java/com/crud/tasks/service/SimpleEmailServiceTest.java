package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


import javax.mail.internet.MimeMessage;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    //@Mock
    //private MialCreatorService mialCreatorService;

    @Test
    public void shouldSenEmail(){
        //Given
        Mail mail = new Mail("test@test.com",null, "Test", "test message", "NORMAL");

        MimeMessagePreparator mimeMessage = simpleEmailService.createMimeMessage(mail);

/*
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());*/

        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

}