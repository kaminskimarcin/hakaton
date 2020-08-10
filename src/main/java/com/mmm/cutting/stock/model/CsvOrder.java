package com.mmm.cutting.stock.model;

import com.opencsv.bean.CsvBindByPosition;

public class CsvOrder {
    private long id;
    private String value;
    private Integer count;

    public CsvOrder(long id, String value, Integer count) {
        this.id = id;
        this.value = value;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
