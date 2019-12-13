package com.hackathon.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ProcessOrderItem {

	@Id 
	private Long id;
		
	@Column(name="Description")
	private String description;
}
