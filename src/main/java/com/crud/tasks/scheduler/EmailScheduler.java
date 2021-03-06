package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MialCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Task: Once a day email";
    private static final String TYPE_SCHEDULED = "SCHEDLUDED";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private MialCreatorService mialCreatorService;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
             //   "Currently in database you got: " + size + " " + getTasks(size)));
                "Currently in database you got: " + size + " " + getTasks(size),
                TYPE_SCHEDULED));
    }

    private static String getTasks(long size) {
        if(size == 1)
            return "task";
        else
            return "tasks";
    }

}
