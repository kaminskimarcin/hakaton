package com.mmm.cuttingstock.controller;


import com.mmm.cuttingstock.model.Purchase;
import com.mmm.cuttingstock.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    private ResponseEntity<List<Purchase>> getAllOrders(){
        var orders = orderService.getOrders();

        return ResponseEntity.ok(orders);
    }

}
