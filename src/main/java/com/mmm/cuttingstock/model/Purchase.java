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
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue
    private Integer id;
    private String salesOrder;
    private String item;
    private String desc;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private Set<SingleOrder> singleOrders = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_id")
    private Set<SingleCut> singleCuts = new HashSet<>();
    private Long jumboWidth;
}
