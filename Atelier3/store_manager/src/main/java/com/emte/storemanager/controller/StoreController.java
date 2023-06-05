package com.emte.storemanager.controller;

import com.emte.dto.StoreTransactionDto;
import com.emte.model.StoreOrder;
import com.emte.storemanager.service.StoreService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @JsonView(Views.StoreView.class)
    @GetMapping("/transactions")
    public List<StoreTransactionDto> getTransactions() {
        return storeService.getTransactions();
    }

    @PostMapping("/sell")
    public boolean sell(@RequestBody @Valid StoreOrder order)
    {
        return storeService.sell(order);
    }

    @PostMapping("/buy")
    public boolean buy(@RequestBody @Valid StoreOrder order)
    {
        return storeService.buy(order);
    }
}

