package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailMessage;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testSendInformationEmail() {
        //Given
        Mail expected = new Mail("test@test.pl",
                null,
                "Tasks: Once a day email",
                "Currently database you got: 2 tasks");
        when(taskRepository.count()).thenReturn(2L);
        when(adminConfig.getAdminMail()).thenReturn(expected.getMailTo());
        //When
        emailScheduler.sendInformationEmail();
        //Then
        verify(simpleEmailService,times(1)).send(anyObject());
    }
}