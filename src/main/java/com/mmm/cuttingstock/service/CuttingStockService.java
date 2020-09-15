package com.mmm.cuttingstock.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmm.cuttingstock.dto.*;
import com.mmm.cuttingstock.producer.CuttingStockProducer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.*;


@Service
public class CuttingStockService {
    private final CuttingStockProducer cuttingStockProducer;
    private final ObjectMapper objectMapper ;
    private final DtoEntityConverter dtoEntityConverter;
    private final RestTemplate restTemplate;

    public CuttingStockService(CuttingStockProducer cuttingStockProducer, ObjectMapper objectMapper,
                               DtoEntityConverter dtoEntityConverter, RestTemplate restTemplate) {
        this.cuttingStockProducer = cuttingStockProducer;
        this.objectMapper = objectMapper;
        this.dtoEntityConverter = dtoEntityConverter;
        this.restTemplate = restTemplate;
    }

    public OrderResponse calculate(OrderDto orderDto) throws IOException {
        List<SingleOrderDto> singleOrders = orderDto.getSingleOrders();
        List<String> uniqueWidth = new ArrayList<>();
        List<String> widthOccurrences = new ArrayList<>();

        HashMap<Long, Long> widthWithOccurrences = new HashMap<>();

        String jumboWidth = String.valueOf(orderDto.getJumboWidth());

        for (SingleOrderDto singleOrder : singleOrders) {
            widthWithOccurrences.merge(singleOrder.getWidth(), singleOrder.getOrderQty(), Long::sum);
        }

        for (Map.Entry<Long, Long> entry : widthWithOccurrences.entrySet()) {
            uniqueWidth.add(entry.getKey().toString());
            widthOccurrences.add(entry.getValue().toString());
        }

        List<LinkedHashMap<String, Integer>> cuttingStockBestSolution = findBestSolution(jumboWidth, uniqueWidth, widthOccurrences);

        return cuttingStockProducer.saveAndGetPreparedResult(cuttingStockBestSolution,
                dtoEntityConverter.convertToOrder(orderDto));
    }

    public List<LinkedHashMap<String, Integer>> findBestSolution(String jumboWidth, List<String> uniqueWidth,
                                                                        List<String> widthOccurrences) throws IOException{

        String uniqueWidthString = uniqueWidth.toString().replace(" ", "");
        String widthOccurrencesString = widthOccurrences.toString().replace(" ", "");
        var results = restTemplate.postForEntity("http://localhost:5000/cuts",
                new CalcOrderDto(jumboWidth, uniqueWidthString, widthOccurrencesString), String.class);

        return objectMapper.readValue(results.getBody(), objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Map.class));
    }
}
