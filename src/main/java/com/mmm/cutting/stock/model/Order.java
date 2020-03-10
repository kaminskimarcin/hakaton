package com.mmm.cutting.stock.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String salesOrder;
    private String item;
    private String desc;
    private List<SingleOrder> singleOrders;
    private Long jumboWidth;
}
