package com.hackathon.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ProcessOrderDefinition {
	
	@Id
	@Column(name="order_ID") 
	private int orderId;
	@Column(name="items")
	@ElementCollection(targetClass=ProcessOrderItem.class)
	private List<ProcessOrderItem> items; 
	
}
