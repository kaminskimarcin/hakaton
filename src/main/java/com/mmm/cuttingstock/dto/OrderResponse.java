package com.mmm.cuttingstock.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private List<SingleCutDto> rawData;
}
