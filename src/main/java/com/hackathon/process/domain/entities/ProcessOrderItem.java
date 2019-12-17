package com.hackathon.process.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
