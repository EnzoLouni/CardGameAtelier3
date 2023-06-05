package com.emte.cardmanager.controller;

import com.emte.cardmanager.service.CardClientService;
import com.emte.cardmanager.service.CardService;
import com.emte.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class CardPrivateController {
    private final CardClientService cardClientService;
    private final CardService cardService;

    @GetMapping("/cards/{id}")
    public CardDto getCard(@PathVariable(name = "id") Integer id) {
        return cardClientService.getCard(id);
    }

    @PutMapping("/cards/{id}")
    public CardDto updateCard(@PathVariable(name = "id") Integer id, @RequestBody @Valid CardDto newCardDto) {
        return cardClientService.updateCard(id, newCardDto);
    }

    @PostMapping("/card")
    public CardDto createCard(@RequestBody @Valid CardDto card)
    {
        return cardService.createCard(card);
    }

    @GetMapping("/cards_to_sell")
    public List<CardDto> getCardsToSell()
    {
        return cardService.getCardsToSell();
    }

    @GetMapping("/generated_cards")
    public List<CardDto> getGeneratedCards()
    {
        return cardClientService.generateCards();
    }

    @GetMapping("/cards/user/{userId}")
    public List<CardDto> getCardsByUser(@PathVariable(name = "userId") Integer userId)
    {
        return cardClientService.getCardsByUser(userId);
    }

    @DeleteMapping("/cards/user/{userId}")
    public boolean deleteCardsByUser(@PathVariable(name = "userId") Integer userId)
    {
        return cardClientService.deleteCardsByUser(userId);
    }
}
