package com.mmm.cuttingstock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmm.cuttingstock.dto.DtoEntityConverter;
import com.mmm.cuttingstock.dto.OrderDto;
import com.mmm.cuttingstock.dto.OrderResponse;
import com.mmm.cuttingstock.dto.SingleOrderDto;
import com.mmm.cuttingstock.producer.CuttingStockProducer;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CuttingStockService {
    private final CuttingStockProducer cuttingStockProducer;
    private final ObjectMapper objectMapper ;
    private final DtoEntityConverter dtoEntityConverter;

    @Value("${script.path}")
    private String scriptPath;

    @Value("${cutting_stock.script}")
    private String cuttingStockScript;


    public CuttingStockService(CuttingStockProducer cuttingStockProducer, ObjectMapper objectMapper,
                               DtoEntityConverter dtoEntityConverter) {
        this.cuttingStockProducer = cuttingStockProducer;
        this.objectMapper = objectMapper;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File(scriptPath + filename);
        return file.getAbsolutePath();
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
        String line = "python " + resolvePythonScriptPath(cuttingStockScript);

        String uniqueWidthString = StringUtils.replaceAll(uniqueWidth.toString(), " ", "");
        String widthOccurrencesString = StringUtils.replaceAll(widthOccurrences.toString(), " ", "");

        Process p = Runtime.getRuntime().exec(line + " " + jumboWidth + " "
                + uniqueWidthString + " " + widthOccurrencesString);

        List<LinkedHashMap<String, Integer>> results;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
            results =  objectMapper.readValue(reader.lines().collect(Collectors.joining()).split("results")[1], List.class);
        }

        destroyProcessAsync(p);

        return results;
    }

    private static void destroyProcessAsync(Process p) {
        CompletableFuture.runAsync(() -> {
            var destroyed = false;

            try {
                destroyed = p.waitFor(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (!destroyed || p.isAlive()) {
                p.destroyForcibly();
            }
        });
    }
}
