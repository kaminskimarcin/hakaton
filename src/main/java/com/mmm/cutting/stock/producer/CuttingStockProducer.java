package com.mmm.cutting.stock.producer;

import com.mmm.cutting.stock.model.CsvOrder;
import com.mmm.cutting.stock.model.OrderResponse;
import com.mmm.cutting.stock.model.SingleCut;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static com.mmm.cutting.stock.service.CuttingStockService.prepareCsvOrderPosition;

@Component
public class CuttingStockProducer {
    private static final String SAMPLE_CSV_FILE = "./sample.csv";
    private static final String[] columns = {"width", "quantity"};

    public OrderResponse prepareResult(List<LinkedHashMap<String, Integer>> response, String jumboWidth,  HttpServletResponse httpServletResponse) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        OrderResponse orderResponse = new OrderResponse();

        List<LinkedHashMap<String, Integer>> nonNullResponse = new ArrayList<>();
        List<SingleCut> singleCutList = new ArrayList<>();

        for (LinkedHashMap<String, Integer> stringIntegerLinkedHashMap : response) {
            LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> entry : stringIntegerLinkedHashMap.entrySet()) {
                if (entry.getValue() != 0) {
                    SingleCut singleCut = new SingleCut(Double.parseDouble(entry.getKey()), entry.getValue());
                    hashMap.put(entry.getKey(), entry.getValue());
                    singleCutList.add(singleCut);
                }
            }
            nonNullResponse.add(hashMap);
        }

        orderResponse.setRawData(singleCutList);

        return orderResponse;
    }

    private OrderResponse prepareTxtResponse(List<LinkedHashMap<String, Integer>> response, OrderResponse orderResponse) {
        return orderResponse;
    }

    private OrderResponse prepareCsvResponse(List<LinkedHashMap<String, Integer>> response, OrderResponse orderResponse,  HttpServletResponse httpServletResponse) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        createCSVFile(response, orderResponse, httpServletResponse);

        return orderResponse;
    }

    public void createCSVFile(List<LinkedHashMap<String, Integer>> response, OrderResponse orderResponse, HttpServletResponse httpServletResponse) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<List<CsvOrder>> csvOrders = prepareCsvOrderPosition(response);

        Workbook workbook = new XSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("CsvOrders");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (List<CsvOrder> csvOrder : csvOrders) {
            for(CsvOrder order: csvOrder) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0)
                        .setCellValue(Double.parseDouble(order.getValue()));

                row.createCell(1)
                        .setCellValue(order.getCount());
            }
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue("");

            row.createCell(1)
                    .setCellValue("");
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("csv-order-response.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();


    }
}
