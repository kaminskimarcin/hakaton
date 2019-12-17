package com.hackathon.process.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROCESS_ORDER_RECEIVER")
@Data
@AllArgsConstructor
public class ProcessOrderReceiver {
    public ProcessOrderReceiver() {
        this.items = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ORDER_iD")
    private long orderId;
    @Column(name = "ITEMS")
    @OneToMany()
    private List<ProcessOrderReceiverItem> items;
}
