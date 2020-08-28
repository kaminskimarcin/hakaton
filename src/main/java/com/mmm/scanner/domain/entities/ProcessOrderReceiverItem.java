package com.mmm.scanner.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Table(name = "PROCESS_ORDER_RECEIVER_ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderReceiverItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ITEM_ID")
    private Long itemId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Min(1)
	@Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "BATCH")
    private int batch;
    @Column(name = "STATUS")
    private String status;


}
