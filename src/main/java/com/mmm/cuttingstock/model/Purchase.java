package com.mmm.cuttingstock.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private String salesOrder;
    private String item;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_Id")
    private Set<SingleOrder> singleOrders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_Id")
    private Set<SingleCut> singleCuts = new HashSet<>();
    private Long jumboWidth;
}
