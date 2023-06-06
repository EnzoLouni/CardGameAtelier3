package com.emte.storemanager;

import com.emte.dto.StoreTransactionDto;
import com.emte.model.StoreOrder;
import com.emte.storemanager.controller.StoreController;
import com.emte.storemanager.service.StoreService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreController.class)
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @JsonView(Views.StoreView.class)
    @Test
    public void testGetTransactions() throws Exception {
        StoreTransactionDto transactionDto = new StoreTransactionDto();
        when(storeService.getTransactions()).thenReturn(Collections.singletonList(transactionDto));

        mockMvc.perform(get("/store/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].action").exists());
    }

    @Test
    public void testSell() throws Exception {
        StoreOrder order = new StoreOrder();
        when(storeService.sell(any())).thenReturn(true);

        mockMvc.perform(post("/store/sell")
                .contentType("application/json")
                .content("{ \"cardId\": 1, \"userId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testBuy() throws Exception {
        StoreOrder order = new StoreOrder();
        when(storeService.buy(any())).thenReturn(true);

        mockMvc.perform(post("/store/buy")
                .contentType("application/json")
                .content("{ \"cardId\": 1, \"userId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
