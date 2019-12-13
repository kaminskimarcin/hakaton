package com.hackathon.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ProcessOrderDefinition {
	
	@Id
	@Column(name="order_ID") 
	private int orderId;
	
	@Column(name="items")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProcessOrderItem> items; 
	
}
