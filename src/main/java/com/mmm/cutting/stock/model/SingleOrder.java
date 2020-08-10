package com.mmm.cutting.stock.model;

import lombok.Data;

@Data
public class SingleOrder {
    private Long procOrd;
    private Long orderQty;
    private Long width;
}
