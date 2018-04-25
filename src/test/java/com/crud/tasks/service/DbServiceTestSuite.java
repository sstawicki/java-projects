package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //Given
        List<Task> taskList = Arrays.asList(
                new Task(1L, "title", "content"),
                new Task(2L, "ttle2", "content2")
        );
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(2, resultList.size());
        assertEquals((Long) 2L, resultList.get(1).getId());

    }

    @Test
    public void saveTask() {
        //Gven
        Task task = new Task(1L, "title", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task result = dbService.saveTask(task);
        //Then
        Long id = task.getId();
        assertEquals(id, result.getId());
    }
}