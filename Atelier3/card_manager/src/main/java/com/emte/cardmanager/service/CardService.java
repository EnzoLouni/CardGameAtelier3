package com.emte.cardmanager.service;

import com.emte.cardmanager.dao.CardRepository;
import com.emte.dto.CardDto;
import com.emte.mapper.CardMapper;
import com.emte.model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardDto getCard(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return cardMapper.toCardDto(card.get());
    }

    public CardDto updateCard(Integer id, CardDto newCardDto) {
        try {
            cardRepository.findById(id);
            newCardDto.setId(id);
            cardRepository.save(cardMapper.toCard(newCardDto));
            return getCard(id);
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    public boolean deleteCard(Integer cardId) {
        try {
            CardDto cardToDelete = getCard(cardId);
            if(cardToDelete.getUserId() == null) {
                cardRepository.deleteById(cardId);
                return true;
            }
            return false;
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    public CardDto createCard(CardDto card) {
        return cardMapper.toCardDto(cardRepository.save(cardMapper.toCard(card)));
    }

    public List<CardDto> getCardsToSell() {
        return cardRepository.findSoldCards().stream().map(cardMapper::toCardDto).collect(toList());
    }

    public List<CardDto> getCards() {
        return StreamSupport.stream(cardRepository.findAll().spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }
}
