package com.mmm.cutting.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.OrderResponse;
import com.mmm.cutting.stock.model.SingleOrder;
import com.mmm.cutting.stock.producer.CuttingStockProducer;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;


import javax.script.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CsvService {
    private final CuttingStockProducer cuttingStockProducer;
    private static ObjectMapper objectMapper ;

    public CsvService(CuttingStockProducer cuttingStockProducer, ObjectMapper objectMapper) {
        this.cuttingStockProducer = cuttingStockProducer;
        this.objectMapper = objectMapper;
    }

   private static String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    public OrderResponse calculate(Order order, HttpServletResponse httpServletResponse) throws IOException, ScriptException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<SingleOrder> singleOrders = order.getSingleOrders();
        HashMap<Long, Long> widthWithOccurrences = new HashMap<>();

        for (SingleOrder singleOrder : singleOrders) {
            widthWithOccurrences.merge(singleOrder.getWidth(), singleOrder.getOrderQty(), Long::sum);
        }

        String jumboWidth = String.valueOf(order.getJumboWidth()-10);
        List<String> uniqueWidth = new ArrayList<>();
        List<String> widthOccurrences = new ArrayList<>();


        for (Map.Entry<Long, Long> entry : widthWithOccurrences.entrySet()) {
            uniqueWidth.add(entry.getKey().toString());
            widthOccurrences.add(entry.getValue().toString());
        }

        List<LinkedHashMap<String, Integer>> cuttingStockBestSolution = findBestSolution(jumboWidth, uniqueWidth, widthOccurrences);

        return cuttingStockProducer.prepareResult(cuttingStockBestSolution, jumboWidth, httpServletResponse);
    }

    public static List<LinkedHashMap<String, Integer>> findBestSolution(String jumboWidth, List<String> uniqueWidth,
                                                                        List<String> widthOccurrences) throws IOException{
        String line = "python " + resolvePythonScriptPath("cssolver.py");
        Process p = Runtime.getRuntime().exec(line + System.lineSeparator() + jumboWidth + System.lineSeparator()
                + uniqueWidth.toString() + System.lineSeparator() + widthOccurrences.toString());

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

    public static void main(String... args) throws IOException, InterruptedException {

//        int count = 1;
//        List<Process> process = new ArrayList<>(21);
//
//        while (count < 5) {
//
//            Process p = Runtime.getRuntime().exec("python C:\\Users\\A8XY6ZZ\\IdeaProjects\\hakaton2\\src\\main\\resources\\cssolver.py 1500 [315,310,165,225,260,275,300,120,185,145,155,135]" +
//                    " [3,1,1,1,2,2,2,1,1,1,3,2]");
//
//            process.add(p);
//
//            System.out.print(" When start " + p.isAlive() + " thread: " + Thread.currentThread().getName() + "\n");
//
//            List<LinkedHashMap<String, Integer>> results = List.of();
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
//                results = objectMapper.readValue(reader.lines().collect(Collectors.joining()).split("results")[1], List.class);
//            }
//
//
//            destroyProcessAsync(p);
//
//            System.out.print(results  + "\n");
//
//            Thread.sleep(10000);
//
//            ++count;
//        }
//
//        System.out.println("Still running processes");
//        process.forEach(p -> System.out.println(" Pid: " + p.pid() + "isAlive: " + p.isAlive() + "\n"));

    }
}
