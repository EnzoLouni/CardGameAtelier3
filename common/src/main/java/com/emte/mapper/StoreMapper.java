package com.emte.mapper;

import com.emte.client.CardClient;
import com.emte.client.UserClient;
import com.emte.dto.StoreTransactionDto;
import com.emte.model.StoreAction;
import com.emte.model.StoreTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring", imports = StoreAction.class)
public abstract class StoreMapper {

    @Autowired
    protected UserClient userClient;

    @Autowired
    protected CardClient cardClient;

    @Mapping(target = "id", source = "transaction.id")
    @Mapping(target = "userDto", expression = "java(userClient.getUser(transaction.getUserId()))")
    @Mapping(target = "cardDto", expression = "java(cardClient.getCard(transaction.getCardId()))")
    @Mapping(target = "action", expression = "java(StoreAction.valueOf(transaction.getAction()))")
    @Mapping(target = "timestamp", source = "transaction.timestamp")
    public abstract StoreTransactionDto toStoreTransactionDto(StoreTransaction transaction);

    @Mapping(target = "id", source = "transactionDto.id")
    @Mapping(target = "userId", expression = "java(transactionDto.getUserDto().getId())")
    @Mapping(target = "cardId", expression = "java(transactionDto.getCardDto().getId())")
    @Mapping(target = "action", expression = "java(transactionDto.getAction().toString())")
    @Mapping(target = "timestamp", source = "transactionDto.timestamp")
    public abstract StoreTransaction toStoreTransaction(StoreTransactionDto transactionDto);
}
