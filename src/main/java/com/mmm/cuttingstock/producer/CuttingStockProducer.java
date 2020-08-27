package com.mmm.cuttingstock.producer;

import com.mmm.cuttingstock.model.SingleCut;
import com.mmm.cuttingstock.model.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CuttingStockProducer {
	private static final String[] columns = {"width", "quantity"};

	public OrderResponse prepareResult(List<LinkedHashMap<String, Integer>> response) {
		OrderResponse orderResponse = new OrderResponse();

		List<LinkedHashMap<String, Integer>> nonNullResponse = new ArrayList<>();
		List<SingleCut> singleCutList = new ArrayList<>();

		int jumboNumber = 1;

		for (LinkedHashMap<String, Integer> stringIntegerLinkedHashMap : response) {
			LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
			for (Map.Entry<String, Integer> entry : stringIntegerLinkedHashMap.entrySet()) {
				if (entry.getValue() != 0) {
					SingleCut singleCut = new SingleCut(Double.parseDouble(entry.getKey()), entry.getValue(), jumboNumber);
					hashMap.put(entry.getKey(), entry.getValue());
					singleCutList.add(singleCut);
				}
			}
			jumboNumber++;
			nonNullResponse.add(hashMap);
		}

		orderResponse.setRawData(singleCutList);

		return orderResponse;
	}
}
