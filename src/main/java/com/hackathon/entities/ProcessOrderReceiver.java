package com.hackathon.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "processOrderReceiver")
@Data
public class ProcessOrderReceiver {
	
	@Id
	@Column(name="order_ID") 
	private int orderId;
	@Column(name="items")
	@OneToMany()
	private List<ProcessOrderReceiverItem> items;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<ProcessOrderReceiverItem> getItems() {
		return items;
	}
	public void setItems(List<ProcessOrderReceiverItem> items) {
		this.items = items;
	}


}
