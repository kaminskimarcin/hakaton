package com.mmm.cutting.stock.service;

import com.mmm.cutting.stock.csv.CsvReader;
import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.SingleOrder;
import com.mmm.cutting.stock.producer.CuttingStockProducer;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import javax.script.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {
    private final CuttingStockProducer cuttingStockProducer;

    public CsvService(CuttingStockProducer cuttingStockProducer) {
        this.cuttingStockProducer = cuttingStockProducer;
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    public void calculate(MultipartFile file) throws IOException, ScriptException {
        Order order = CsvReader.readCsv(file);
        List<SingleOrder> singleOrders = order.getSingleOrders();
        HashMap<Long, Long> widthWithOccurrences = new HashMap<>();

        for (SingleOrder singleOrder : singleOrders) {
            widthWithOccurrences.merge(singleOrder.getWidth(), singleOrder.getOrderQuantity(), Long::sum);
        }

        String jumboWidth = order.getJumboWidth().toString();
        List<String> uniqueWidth = new ArrayList<>();
        List<String> widthOccurrences = new ArrayList<>();


        for (Map.Entry<Long, Long> entry : widthWithOccurrences.entrySet()) {
            uniqueWidth.add(entry.getKey().toString());
            widthOccurrences.add(entry.getValue().toString());
        }

        List<String> response = findBestSolution(jumboWidth, uniqueWidth, widthOccurrences);

        cuttingStockProducer.prepareResult(response);
    }

    private List<String> findBestSolution(String jumboWidth, List<String> uniqueWidth, List<String> widthOccurrences) throws IOException, ScriptException {
        String line = "python " + resolvePythonScriptPath("cssolver.py");
        CommandLine cmdLine = CommandLine.parse(line);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        String[] params = new String[]{jumboWidth, uniqueWidth.toString(), widthOccurrences.toString()};

        int exitCode = executor.execute(cmdLine);

    /*
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader(resolvePythonScriptPath("cssolver.py")), context);*/

        return new ArrayList<>();
    }
}
