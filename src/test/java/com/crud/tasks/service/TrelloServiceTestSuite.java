package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;



    @Test
    public void shouldfetchEmptyList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDto = trelloService.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDto);
        assertEquals(0, trelloBoardDto.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> list = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(
                new TrelloBoardDto("1","test", list),
                new TrelloBoardDto("2", "test2", list)
        );

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(2, trelloBoardDtos1.size());
    }

    @Test
    public void testCreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "description", "top", "id1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","test","https://test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", createdTrelloCardDto1.getId());
        assertEquals("test", createdTrelloCardDto1.getName());
        assertEquals("https://test.com", createdTrelloCardDto1.getShortUrl());

   }
}