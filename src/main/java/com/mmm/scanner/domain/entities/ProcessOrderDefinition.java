package com.mmm.scanner.domain.entities;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "PROCESS_ORDER_DEFINITION")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderDefinition {
	@Id
	@Column(name="order_ID") 
	private Long orderId;
	@Column(name="items")
	@ManyToMany()
	private List<ProcessOrderItem> items;
}
