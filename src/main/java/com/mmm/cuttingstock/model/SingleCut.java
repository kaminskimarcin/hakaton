package com.mmm.cuttingstock.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class SingleCut {

    @Id
    @GeneratedValue
    private Integer id;
    private double value;
    private int quantity;
    private int jumboNumber;
    private boolean isChecked;

}


