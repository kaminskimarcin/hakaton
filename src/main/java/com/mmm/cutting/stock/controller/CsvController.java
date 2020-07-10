package com.mmm.cutting.stock.controller;

import com.mmm.cutting.stock.csv.CsvReader;
import com.mmm.cutting.stock.service.CsvService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptException;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class CsvController {
    private final CsvService csvService;

    @PostMapping(value = "/input-csv")
    private void inputCsvFile(@RequestParam("file") MultipartFile file) throws IOException, ScriptException {
        csvService.calculate(file);
    }
}
