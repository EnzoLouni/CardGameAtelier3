package com.emte.client;

import com.emte.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "cardClient", url = "http://localhost:8082/cardapi")
public interface CardClient {

    @GetMapping("/private/cards/{id}")
    CardDto getCard(@PathVariable(name = "id") Integer id);

    @PutMapping("/private/cards/{id}")
    CardDto updateCard(@PathVariable(name = "id") Integer id, @RequestBody @Valid CardDto newCardDto);

    @PostMapping("/private/card")
    CardDto createCard(@RequestBody @Valid CardDto card);

    @GetMapping("/private/cards_to_sell")
    List<CardDto> getCardsToSell();

    @GetMapping("/private/generated_cards")
    List<CardDto> getGeneratedCards();

    @GetMapping("/private/cards/user/{userId}")
    List<CardDto> getCardsByUser(@PathVariable(name = "userId") Integer userId);

    @DeleteMapping("/private/cards/user/{userId}")
    boolean deleteCardsByUser(@PathVariable(name = "userId") Integer userId);
}
