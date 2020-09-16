package com.mmm.cuttingstock.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class SingleOrder {

    @Id
    @GeneratedValue
    private Long id;
    private Long procOrd;
    private Long orderQty;
    private Long width;

}
