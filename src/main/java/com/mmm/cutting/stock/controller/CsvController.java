package com.mmm.cutting.stock.controller;

import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.OrderResponse;
import com.mmm.cutting.stock.service.CsvService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class CsvController {
    private final CsvService csvService;

    @PostMapping(value = "/input-csv")
    private OrderResponse inputCsvFile(@RequestBody Order order, HttpServletResponse response) throws IOException, ScriptException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + "cuttingstock.csv" + "\"");

        return csvService.calculate(order, response);
    }
}
