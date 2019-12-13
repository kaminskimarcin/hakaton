package com.hackathon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.dtos.ProcessOrderReceiverDTO;
import com.hackathon.dtos.ProcessOrderReceiverItemDTO;
import com.hackathon.entities.ProcessOrderReceiver;
import com.hackathon.entities.ProcessOrderReceiverItem;
import com.hackathon.repositories.ProcessOrderReceiverItemRepository;
import com.hackathon.repositories.ProcessOrderReceiverRepository;
import com.hackathon.services.impl.SaveReportToFile;

@Controller
public class ProcessOrderController {
	
	@Autowired
	ProcessOrderReceiverRepository orderRepo;
	
	@Autowired
	ProcessOrderReceiverItemRepository itemRepo;
	
	@Autowired
	SaveReportToFile saveFileReportService;
	
	@RequestMapping(value = "/submitCompletedItemsListForProcess" , method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<?> updateProcessOrder(@RequestBody ProcessOrderReceiverDTO receiverdto)
	{
		ProcessOrderReceiver receiever = new ProcessOrderReceiver();
		receiever.setOrderId(receiverdto.getOrderId());
		for (ProcessOrderReceiverItemDTO item: receiverdto.getItems()) {
			ProcessOrderReceiverItem newItem = new ProcessOrderReceiverItem();
			newItem.setBatch(item.getBatch());
			newItem.setDescription(item.getDescription());
			newItem.setId(item.getId());
			newItem.setQuantity(item.getQuantity());
			receiever.getItems().add(newItem);
		}
		receiever.setItems(receiever.getItems());
		itemRepo.saveAll(receiever.getItems());
		orderRepo.save(receiever);
		saveFileReportService.saveProcessOrderListToFile(receiever);
		
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
