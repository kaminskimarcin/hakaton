package com.mmm.cuttingstock.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingleCutDto {
    private int id;
    private double value;
    private int quantity;
    private int jumboNumber;
    private boolean isChecked;

    public SingleCutDto(final double value, final int quantity, final int jumboNumber){
        this.value= value;
        this.quantity = quantity;
        this.jumboNumber = jumboNumber;
    }
}
