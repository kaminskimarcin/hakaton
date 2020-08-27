package com.mmm.cuttingstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CsvOrder {
    private long id;
    private String value;
    private Integer count;
}
