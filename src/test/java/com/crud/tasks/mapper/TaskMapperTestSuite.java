package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title","test_content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals((Long)1L, task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("test_content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title","test_content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals((Long)1L, taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("test_content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = Arrays.asList(
                new Task(1L, "title","test_content"),
                new Task(2L, "title2","test_content_2")
        );
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(2, taskDtoList.size());
        assertEquals((Long) 2L, taskDtoList.get(1).getId());
        assertEquals("test_content", taskDtoList.get(0).getContent());

    }
}