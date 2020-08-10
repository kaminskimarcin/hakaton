package com.mmm.cutting.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmm.cutting.stock.csv.CsvReader;
import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.OrderResponse;
import com.mmm.cutting.stock.model.SingleOrder;
import com.mmm.cutting.stock.producer.CuttingStockProducer;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.script.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
public class CsvService {
    private final CuttingStockProducer cuttingStockProducer;
    private final ObjectMapper objectMapper;

    public CsvService(CuttingStockProducer cuttingStockProducer, ObjectMapper objectMapper) {
        this.cuttingStockProducer = cuttingStockProducer;
        this.objectMapper = objectMapper;
    }

   private String resolvePythonScriptPath(String filename) {
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

    private List<LinkedHashMap<String, Integer>> findBestSolution(String jumboWidth, List<String> uniqueWidth, List<String> widthOccurrences) throws IOException, ScriptException {
        String line = "python " + resolvePythonScriptPath("cssolver.py");
        String resultFileName = "result.txt";
        String resultFile = "src/main/resources/result.txt";
        String[] params = new String[]{jumboWidth, uniqueWidth.toString(), widthOccurrences.toString(), resultFile};

        CommandLine cmdLine = CommandLine.parse(line);
        cmdLine.addArguments(params);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        executor.execute(cmdLine);

        InputStream file = getClass().getClassLoader().getResourceAsStream(resultFileName);

        return objectMapper.readValue(file, List.class);
    }


//    public static void main(String... args) throws IOException {
//        String[] params = new String[]{"1500", "[315,310,165,225,260,275,300,120,185,145,155,135]",
//                "[3,1,1,1,2,2,2,1,1,1,3,2]"};
//
//        String line = "python " + resolvePythonScriptPathS("cssolver.py");
//        CommandLine cmdLine = CommandLine.parse(line);
//        cmdLine.addArguments(new String[]{"1500", "[315,310,165,225,260,275,300,120,185,145,155,135]",
//                "[3,1,1,1,2,2,2,1,1,1,3,2]", "src/main/resources/result.txt"});
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
//
//        DefaultExecutor executor = new DefaultExecutor();
//        executor.setStreamHandler(streamHandler);
//
//
//        int exitCode = executor.execute(cmdLine);
//    }
//
//
//    private static String resolvePythonScriptPathS(String filename) {
//        File file = new File("src/main/resources/" + filename);
//        return file.getAbsolutePath();
//    }

}
