package com.emte.cardmanager.controller;

import com.emte.cardmanager.service.CardService;
import com.emte.dto.CardDto;
import com.emte.model.RequestCreationCard;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class CardPublicController {

    private final CardService cardService;

    @JsonView(Views.CardView.class)
    @GetMapping("/cards/{id}")
    public CardDto getCard(@PathVariable(name = "id") Integer id) {
        return cardService.getCard(id);
    }

    @JsonView(Views.CardView.class)
    @PutMapping("/cards/{id}")
    public CardDto updateCard(@PathVariable(name = "id") Integer id, @RequestBody @Valid CardDto newCardDto) {
        return cardService.updateCard(id, newCardDto);
    }

    @DeleteMapping("/cards/{id}")
    public boolean deleteCard(@PathVariable(name = "id") Integer id) {
        return cardService.deleteCard(id);
    }

    @JsonView(Views.CardView.class)
    @PostMapping("/card")
    public CardDto createCard(@RequestBody @Valid RequestCreationCard request)
    {
        return cardService.createCard(request);
    }

    @JsonView(Views.CardView.class)
    @GetMapping("/cards_to_sell")
    public List<CardDto> getCardsToSell()
    {
        return cardService.getCardsToSell();
    }

    @JsonView(Views.CardView.class)
    @GetMapping("/cards")
    public List<CardDto> getCards()
    {
        return cardService.getCards();
    }
}
