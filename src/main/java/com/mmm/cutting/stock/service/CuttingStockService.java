package com.mmm.cutting.stock.service;

import com.mmm.cutting.stock.model.CsvOrder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CuttingStockService {
    public static List<List<CsvOrder>> prepareCsvOrderPosition(List<LinkedHashMap<String, Integer>> response) {
        List<List<CsvOrder>> csvOrderList = new ArrayList<>();
        int i = 1;

        for (LinkedHashMap<String, Integer> hashMap : response) {
            List<CsvOrder> csvOrders = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                CsvOrder csvOrder = new CsvOrder(i, entry.getKey(), entry.getValue());
                csvOrders.add(csvOrder);
            }
            i++;
            csvOrderList.add(csvOrders);
        }

        return csvOrderList;
    }
}
