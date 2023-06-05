package com.emte.cardmanager.service;

import com.emte.cardmanager.dao.CardRepository;
import com.emte.dto.CardDto;
import com.emte.mapper.CardMapper;
import com.emte.model.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class CardClientService {
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
        newCardDto.setId(id);
        cardRepository.save(cardMapper.toCard(newCardDto));
        return getCard(id);
    }

    public List<CardDto> getCardsByUser(Integer userId) {
        return cardRepository.findCardsByUserId(userId).stream().map(cardMapper::toCardDto).collect(toList());
    }

    public boolean deleteCardsByUser(Integer userId) {
        try
        {
            List<CardDto> cardDtos = getCardsByUser(userId);
            cardDtos.forEach(cardDto -> cardDto.setUserId(null));
            cardRepository.saveAll(cardDtos.stream().map(cardMapper::toCard).collect(toList()));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public List<CardDto> generateCards() {
        ClassPathResource staticDataResource = new ClassPathResource("Types_of_Cards.json");
        List<CardDto> generatedCards = new ArrayList<>();
        String staticDataString;
        try {
            staticDataString = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        do {
            JSONObject cardJson = new JSONObject(staticDataString.substring(1,staticDataString.indexOf('}')+1));
            generatedCards.add(CardDto.builder()
                    .name(cardJson.getString("name"))
                    .description(cardJson.getString("description"))
                    .family(cardJson.getString("family"))
                    .affinity(cardJson.getString("affinity"))
                    .imgUrl(cardJson.getString("imgUrl"))
                    .smallImgUrl(cardJson.getString("smallImgUrl"))
                    .energy(cardJson.getDouble("energy"))
                    .hp(cardJson.getDouble("hp"))
                    .defense(cardJson.getDouble("defense"))
                    .attack(cardJson.getDouble("attack"))
                    .price(cardJson.getDouble("price"))
                    .build());
            staticDataString = staticDataString.substring(staticDataString.indexOf('}')+1);
        } while(staticDataString.indexOf('}') > -1);

        return generatedCards;
    }
}
