package com.emte.storemanager.controller;

import com.emte.dto.StoreTransactionDto;
import com.emte.model.StoreOrder;
import com.emte.storemanager.service.StoreService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @JsonView(Views.StoreView.class)
    @Test
    public void testGetTransactions() throws Exception {
        MvcResult result = mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        JSONArray transactions = new JSONArray(responseBody);
        assertTrue(transactions.length() > 0);
        JSONObject transaction = transactions.getJSONObject(0);
        assertTrue(transaction.has("action"));
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
