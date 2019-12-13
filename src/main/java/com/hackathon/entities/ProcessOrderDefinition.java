package com.hackathon.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ProcessOrderDefinition {
	
	@Id
	private Long orderId;
	@ElementCollection(targetClass=Component.class)
	private List<Component> components; 
	
}
