package com.emte.cardmanager.service;

import com.emte.cardmanager.dao.CardRepository;
import com.emte.dto.CardDto;
import com.emte.mapper.CardMapper;
import com.emte.model.Card;
import com.emte.model.RequestCreationCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardDto getCard(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isEmpty()) {
            return null;
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
            return null;
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
            return false;
        }
    }

    public CardDto createCard(RequestCreationCard request) {
        CardDto newCardDto = CardDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .affinity(request.getAffinity())
                .attack(request.getAttack())
                .energy(request.getEnergy())
                .defense(request.getDefense())
                .family(request.getFamily())
                .hp(request.getHp())
                .imgUrl(request.getImgUrl())
                .smallImgUrl(request.getSmallImgUrl())
                .price(request.getPrice())
                .build();
        try {
            return cardMapper.toCardDto(cardRepository.save(cardMapper.toCard(newCardDto)));
        } catch (Exception e) {
            return null;
        }
    }

    public CardDto createCard(CardDto cardDto) {
        try {
            return cardMapper.toCardDto(cardRepository.save(cardMapper.toCard(cardDto)));
        } catch (Exception e) {
            return null;
        }
    }

    public List<CardDto> getCardsToSell() {
        return cardRepository.findSoldCards().stream().map(cardMapper::toCardDto).collect(toList());
    }

    public List<CardDto> getCards() {
        return StreamSupport.stream(cardRepository.findAll().spliterator(),false).map(cardMapper::toCardDto).collect(toList());
    }
}
