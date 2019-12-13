package com.hackathon.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Table(name = "processOrder")
@Data
public class ProcessOrder {
	
	@Id
	private long id;
	
	private long idComponent;
	
	private String desc;
	
	@Min(1)
	private int quantity;
	
	private int batch;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdComponent() {
		return idComponent;
	}

	public void setIdComponent(long idComponent) {
		this.idComponent = idComponent;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}
	
	
	
	
	
	

}
