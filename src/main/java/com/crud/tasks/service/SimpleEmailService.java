package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MialCreatorService mialCreatorService;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
      try {
      //   SimpleMailMessage mailMessage = createMailMessage(mail);
           javaMailSender.send(createMimeMessage(mail));
          LOGGER.info("Email has been sent.");
      } catch (MailException e) {
          LOGGER.error("Failed to process mail sending: ", e.getMessage(), e);
      }
    }
        private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());

        if(mail.getToCc()!= null) {
        mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage (final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setSubject(mail.getSubject());
            if (mail.getType().equals("SCHEDLUDED")) {
                mimeMessageHelper.setText(mialCreatorService.buildSchedulerEmail(mail.getMessage()), true);
            } else
               mimeMessageHelper.setText(mialCreatorService.buildTrelloCardEmail(mail.getMessage()), true);

        };
    }

}
