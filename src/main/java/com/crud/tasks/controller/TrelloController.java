package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin("*")
public class TrelloController {

        @Autowired
        private TrelloService trelloService;

        @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
        public List<TrelloBoardDto> getTrelloBoards() {

            return trelloService.fetchTrelloBoards();
                    /*.stream()
                    .filter(trelloBoard -> trelloBoard.getName().contains("Kodilla"))
                    .collect(Collectors.toList());

            trelloBoards.forEach(trelloBoardDto -> {System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());

            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed())); });*/

        }

        @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
        public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
            return trelloService.createdTrelloCard(trelloCardDto);
        }

}
