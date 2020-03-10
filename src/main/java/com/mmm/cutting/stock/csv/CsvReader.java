package com.mmm.cutting.stock.csv;

import com.mmm.cutting.stock.model.Order;
import com.mmm.cutting.stock.model.SingleOrder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvReader {
    public static void readCsv(MultipartFile multipartFile) throws IOException {
        Workbook wb = new XSSFWorkbook(multipartFile.getInputStream());

        Iterator<Row> rowItr = wb.getSheetAt(0).iterator();
        Order order = new Order();
        List<SingleOrder> singleOrders = new ArrayList<>();
        while (rowItr.hasNext()) {
            Row row = rowItr.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellItr = row.cellIterator();
            SingleOrder singleOrder = new SingleOrder();
            while (cellItr.hasNext()) {

                Cell cell = cellItr.next();
                int index = cell.getColumnIndex();
                switch (index) {
                    case 0:
                        if (cell.getCellType() != CellType.BLANK) {
                            order.setSalesOrder(String.valueOf(getValueFromCell(cell)));
                        }
                        break;
                    case 1:
                        if (cell.getCellType() != CellType.BLANK) {
                            order.setItem(String.valueOf(getValueFromCell(cell)));
                        }
                        break;
                    case 2:
                        if (cell.getCellType() != CellType.BLANK) {
                            order.setDesc((String) getValueFromCell(cell));
                        }
                        break;
                    case 3:
                        if (cell.getCellType() != CellType.BLANK) {
                            singleOrder.setProcOrderId((Long) getValueFromCell(cell));
                        }
                        break;
                    case 4:
                        if (cell.getCellType() != CellType.BLANK) {
                            singleOrder.setOrderQuantity((Long) getValueFromCell(cell));
                        }
                        break;
                    case 5:
                        if (cell.getCellType() != CellType.BLANK) {
                            singleOrder.setWidth((Long) getValueFromCell(cell));
                        }
                        break;
                    case 6:
                        if (cell.getCellType() != CellType.BLANK) {
                            order.setJumboWidth((Long) getValueFromCell(cell));
                        }
                        break;
                }
            }
            singleOrders.add(singleOrder);
        }
        order.setSingleOrders(singleOrders);

        System.out.println(order.toString());
    }

    private static Object getValueFromCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return Long.valueOf(((XSSFCell) cell).getRawValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
