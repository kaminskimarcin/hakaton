package com.mmm.cuttingstock.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String salesOrder;
    private String item;
    private String desc;
    private List<SingleOrderDto> singleOrders;
    private Long jumboWidth;
}
