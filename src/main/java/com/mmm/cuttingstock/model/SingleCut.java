package com.mmm.cuttingstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleCut {
    private double value;
    private int quantity;
    private int jumboNumber;
}
