package com.hackathon.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Component {

	@Id 
	private Long id;
	
	private String desc;
}
