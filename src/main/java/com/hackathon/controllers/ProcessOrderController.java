package com.hackathon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hackathon.entities.ProcessOrderReceiver;
import com.hackathon.repositories.ProcessOrderReceiverItemRepository;
import com.hackathon.repositories.ProcessOrderReceiverRepository;
import com.hackathon.services.impl.SaveReportToFile;

@RestController
@CrossOrigin("*")
public class ProcessOrderController {
	
	@Autowired
	ProcessOrderReceiverRepository orderRepo;
	
	@Autowired
	ProcessOrderReceiverItemRepository itemRepo;
	
	@Autowired
	SaveReportToFile saveFileReportService;
	
	@RequestMapping(value = "/submitCompletedItemsListForProcess" , method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<?> updateProcessOrder(@RequestBody ProcessOrderReceiver receiver)
	{
		itemRepo.saveAll(receiver.getItems());
		orderRepo.save(receiver);
		saveFileReportService.saveProcessOrderListToFile(receiver);
		
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
