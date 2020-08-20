package com.mmm.cutting.stock.model;

public class SingleCut {
    private double value;
    private int quantity;
    private int jumboNumber;

    public SingleCut(double value, int quantity, int jumboNumber) {
        this.value = value;
        this.quantity = quantity;
        this.jumboNumber = jumboNumber;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getJumboNumber() {
        return jumboNumber;
    }

    public void setJumboNumber(int jumboNumber) {
        this.jumboNumber = jumboNumber;
    }
}
