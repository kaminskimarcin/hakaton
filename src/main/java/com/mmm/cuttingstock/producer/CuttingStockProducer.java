package com.mmm.cuttingstock.producer;

import com.mmm.cuttingstock.dto.DtoEntityConverter;
import com.mmm.cuttingstock.dto.SingleCutDto;
import com.mmm.cuttingstock.dto.OrderResponse;
import com.mmm.cuttingstock.model.Purchase;
import com.mmm.cuttingstock.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CuttingStockProducer {
    private static final String[] columns = {"width", "quantity"};

    private final OrderRepository orderRepository;
    private final DtoEntityConverter dtoEntityConverter;

    public CuttingStockProducer(OrderRepository orderRepository, DtoEntityConverter dtoEntityConverter) {
        this.orderRepository = orderRepository;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    public OrderResponse saveAndGetPreparedResult(List<LinkedHashMap<String, Integer>> solution, Purchase order) {
        OrderResponse orderResponse = new OrderResponse();

        List<LinkedHashMap<String, Integer>> nonNullResponse = new ArrayList<>();
        List<SingleCutDto> singleCutListDto = new ArrayList<>();

        int jumboNumber = 1;

        for (LinkedHashMap<String, Integer> stringIntegerLinkedHashMap : solution) {
            LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : stringIntegerLinkedHashMap.entrySet()) {
                if (entry.getValue() != 0) {
                    SingleCutDto singleCut = new SingleCutDto(Double.parseDouble(entry.getKey()), entry.getValue(), jumboNumber);
                    hashMap.put(entry.getKey(), entry.getValue());
                    singleCutListDto.add(singleCut);
                }
            }
            jumboNumber++;
            nonNullResponse.add(hashMap);
        }

        var singleCuts = dtoEntityConverter.convertAllToSingleCuts(singleCutListDto);

        Purchase savedOrder = orderRepository.save(order);
        savedOrder.setSingleCuts(singleCuts);

        savedOrder = orderRepository.save(savedOrder);

        orderResponse.setRawData(dtoEntityConverter.convertAllToSingleCutDTOs(savedOrder.getSingleCuts()));

        orderResponse.getRawData()
                .sort(Comparator.comparingInt(SingleCutDto::getJumboNumber));

        return orderResponse;
    }
}
