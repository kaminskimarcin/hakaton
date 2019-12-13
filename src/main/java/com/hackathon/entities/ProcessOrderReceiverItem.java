package com.hackathon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class ProcessOrderReceiverItem {
	
	@Id 
	@Column(name="Item_ID")
	private Long id;
	
	@Column(name="Description")
	private String description;
	
	@Min(1)
	private int quantity;
	
	private int batch;

	
	

}
