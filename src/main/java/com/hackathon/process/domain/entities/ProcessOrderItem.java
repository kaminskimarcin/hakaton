package com.hackathon.process.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "PROCESS_ORDER_ITEM")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderItem {
	@Id 
	private Long id;
	@Column(name="DESCRIPTION")
	private String description;
}
