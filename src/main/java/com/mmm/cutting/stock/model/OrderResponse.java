package com.mmm.cutting.stock.model;

import java.util.List;

public class OrderResponse {
    private List<SingleCut> rawData;

    public List<SingleCut> getRawData() {
        return rawData;
    }

    public void setRawData(List<SingleCut> rawData) {
        this.rawData = rawData;
    }
}
