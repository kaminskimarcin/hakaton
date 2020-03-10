package com.mmm.cutting.stock.model;

import lombok.Data;

@Data
public class SingleOrder {
    private Long procOrderId;
    private Long orderQuantity;
    private Long width;
}
