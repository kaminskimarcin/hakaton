package com.hackathon.dtos;

import java.util.List;

import com.hackathon.entities.ProcessOrderReceiverItem;


public class ProcessOrderReceiverDTO {
	

	private int orderId;

	private List<ProcessOrderReceiverItemDTO> items;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<ProcessOrderReceiverItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ProcessOrderReceiverItemDTO> items) {
		this.items = items;
	}


}
