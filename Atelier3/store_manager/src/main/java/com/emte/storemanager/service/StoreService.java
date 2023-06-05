package com.emte.storemanager.service;

import com.emte.client.CardClient;
import com.emte.client.UserClient;
import com.emte.dto.CardDto;
import com.emte.dto.StoreTransactionDto;
import com.emte.dto.UserDto;
import com.emte.mapper.StoreMapper;
import com.emte.model.StoreAction;
import com.emte.model.StoreOrder;
import com.emte.storemanager.dao.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Validated
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;
    private final CardClient cardClient;
    private final UserClient userClient;

    public List<StoreTransactionDto> getTransactions() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false).map(storeMapper::toStoreTransactionDto).collect(toList());
    }

    public boolean sell(StoreOrder order) {
        CardDto cardSold = cardClient.getCard(order.getCardId());
        if(cardSold.getUserId() == order.getUserId()) {
            cardSold.setUserId(null);
            cardClient.updateCard(cardSold.getId(), cardSold);
            StoreTransactionDto newStoreTransactionDto = StoreTransactionDto.builder()
                    .cardDto(cardSold)
                    .userDto(userClient.getUser(order.getUserId()))
                    .action(StoreAction.SELL)
                    .timestamp(new Date())
                    .build();
            storeRepository.save(storeMapper.toStoreTransaction(newStoreTransactionDto));
            return true;
        }
        return false;
    }

    public boolean buy(StoreOrder order) {
        CardDto cardBought = cardClient.getCard(order.getCardId());
        if(cardBought.getUserId() == null) {
            UserDto newOwner = userClient.getUser(order.getUserId());
            cardBought.setUserId(newOwner.getId());
            cardClient.updateCard(cardBought.getId(), cardBought);
            StoreTransactionDto newStoreTransactionDto = StoreTransactionDto.builder()
                    .cardDto(cardBought)
                    .userDto(newOwner)
                    .action(StoreAction.BUY)
                    .timestamp(new Date())
                    .build();
            storeRepository.save(storeMapper.toStoreTransaction(newStoreTransactionDto));
            return true;
        }
        return false;
    }
}
