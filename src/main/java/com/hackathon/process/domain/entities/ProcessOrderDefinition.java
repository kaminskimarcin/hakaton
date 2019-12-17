package com.hackathon.process.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderDefinition {
	@Id
	@Column(name="order_ID") 
	private Long orderId;
	@Column(name="items")
	@OneToMany()
	private List<ProcessOrderItem> items;
}
