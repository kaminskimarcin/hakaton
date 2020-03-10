package com.mmm.cutting.stock.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.mmm.cutting.stock.csv.CsvReader.readCsv;

@CrossOrigin("*")
@RestController
public class CsvController {
    @PostMapping(value = "/input-csv")
    private void inputCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
        readCsv(file);
    }
}
