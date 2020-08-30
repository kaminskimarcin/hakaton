package com.mmm.cuttingstock.service;


import com.mmm.cuttingstock.model.Purchase;
import com.mmm.cuttingstock.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    final private OrderRepository repository;


    public OrderService(final OrderRepository repository) {
        this.repository = repository;
    }

    public List<Purchase> getOrders(){
        return repository.findAll();
    }
}
