package com.emte.storemanager;

import com.emte.client.CardClient;
import com.emte.client.UserClient;
import com.emte.dto.CardDto;
import com.emte.dto.StoreTransactionDto;
import com.emte.dto.UserDto;
import com.emte.mapper.StoreMapper;
import com.emte.model.StoreOrder;
import com.emte.model.StoreTransaction;
import com.emte.storemanager.dao.StoreRepository;
import com.emte.storemanager.service.StoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StoreServiceTests {

    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private StoreMapper storeMapper;

    @MockBean
    private CardClient cardClient;

    @MockBean
    private UserClient userClient;

    @InjectMocks
    private StoreService storeService;

    @Test
    public void testGetTransactions() {
        StoreTransactionDto transactionDto = new StoreTransactionDto();
        when(storeRepository.findAll()).thenReturn(Collections.singletonList(new StoreTransaction()));
        when(storeMapper.toStoreTransactionDto(any())).thenReturn(transactionDto);

        List<StoreTransactionDto> transactions = storeService.getTransactions();

        assertEquals(1, transactions.size());
        assertEquals(transactionDto, transactions.get(0));
    }

    @Test
    public void testSell_WithValidOrder() {
        StoreOrder order = new StoreOrder();
        CardDto cardSold = new CardDto();
        UserDto userDto = new UserDto();
        StoreTransactionDto transactionDto = new StoreTransactionDto();

        cardSold.setUserId(order.getUserId());
        when(cardClient.getCard(order.getCardId())).thenReturn(cardSold);
        when(userClient.getUser(order.getUserId())).thenReturn(userDto);
        when(storeMapper.toStoreTransactionDto(any())).thenReturn(transactionDto);

        boolean sold = storeService.sell(order);

        assertTrue(sold);
    }

    @Test
    public void testSell_WithInvalidOrder() {
        StoreOrder order = StoreOrder.builder()
                .cardId(1)
                .userId(1)
                .build();
        CardDto cardSold = new CardDto();

        cardSold.setUserId(order.getUserId() + 1); // ID d'utilisateur est différent
        when(cardClient.getCard(order.getCardId())).thenReturn(cardSold);

        boolean sold = storeService.sell(order);

        assertFalse(sold);
    }

    @Test
    public void testBuy_WithValidOrder() {
        StoreOrder order = new StoreOrder();
        CardDto cardBought = new CardDto();
        UserDto newOwner = new UserDto();
        StoreTransactionDto transactionDto = new StoreTransactionDto();

        cardBought.setUserId(null);
        when(cardClient.getCard(order.getCardId())).thenReturn(cardBought);
        when(userClient.getUser(order.getUserId())).thenReturn(newOwner);
        when(storeMapper.toStoreTransactionDto(any())).thenReturn(transactionDto);

        boolean bought = storeService.buy(order);

        assertTrue(bought);
    }

    @Test
    public void testBuy_WithInvalidOrder() {
        StoreOrder order = new StoreOrder();
        CardDto cardBought = new CardDto();

        cardBought.setUserId(order.getUserId()); // La carte appartiens déjà a quelqu'un
        when(cardClient.getCard(order.getCardId())).thenReturn(cardBought);

        boolean bought = storeService.buy(order);

        assertFalse(bought);
    }
}
