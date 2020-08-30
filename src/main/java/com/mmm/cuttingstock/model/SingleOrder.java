package com.mmm.cuttingstock.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "single_order")
public class SingleOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Long procOrd;
    private Long orderQty;
    private Long width;
}
