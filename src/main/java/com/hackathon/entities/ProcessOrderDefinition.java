package com.hackathon.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ProcessOrderDefinition {
	
	@Id
	@Column(name="order_ID") 
	private long orderId;
	
	@Column(name="items")
	@OneToMany()
	private List<ProcessOrderItem> items;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<ProcessOrderItem> getItems() {
		return items;
	}

	public void setItems(List<ProcessOrderItem> items) {
		this.items = items;
	} 
	
}
