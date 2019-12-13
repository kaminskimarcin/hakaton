package com.hackathon.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ProcessOrderItem {

	@Id 
	@Column(name="Item_ID")
	private Long id;
	
	@Column(name="Description")
	private String description;
}
