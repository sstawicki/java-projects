package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import javafx.beans.binding.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = Arrays.asList(
                new TrelloBoardDto("1","Task", trelloListDto),
                new TrelloBoardDto("2", "Task 2", trelloListDto)
        );
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Task 2", trelloBoards.get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Gven
        List<TrelloList> trelloList = new ArrayList<>();
        List<TrelloBoard> trelloBoard = Arrays.asList(
                new TrelloBoard("1","Task", trelloList),
                new TrelloBoard("2", "Task 2", trelloList)
        );
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertEquals(2, trelloBoardsDto.size());
        assertEquals("2", trelloBoardsDto.get(1).getId());
        assertEquals("Task", trelloBoardsDto.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = Arrays.asList(
                new TrelloListDto("1", "Task", true),
                new TrelloListDto("2", "Task 2", false)
        );
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(true, trelloLists.get(0).isClosed());
        assertEquals("2", trelloLists.get(1).getId());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("1", "Task", true),
                new TrelloList("2", "Task 2", false)
        );
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(false, trelloListDto.get(1).isClosed());
        assertEquals("1", trelloListDto.get(0).getId());
    }

    @Test
    public void testMapToCardDto() {
        //Gven
        TrelloCardDto trelloCardDto = new TrelloCardDto("Task", "Description", "top", "id1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("top", trelloCard.getPos());
        assertEquals("id1", trelloCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Gven
        TrelloCard trelloCard = new TrelloCard("Task", "Description", "top", "id1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Task", trelloCardDto.getName());
        assertEquals("Description", trelloCardDto.getDescription());
    }
}