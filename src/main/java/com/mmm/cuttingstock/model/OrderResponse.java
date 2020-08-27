package com.mmm.cuttingstock.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private List<SingleCut> rawData;
}
