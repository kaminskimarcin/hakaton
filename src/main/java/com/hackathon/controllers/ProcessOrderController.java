package com.hackathon.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.entities.ProcessOrderReceiver;
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
	
	@RequestMapping(value = "/test" , method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public void updateProcessOrder(@RequestBody ProcessOrderReceiver receiver)
	{
		itemRepo.saveAll(receiver.getItems());
		orderRepo.save(receiver);
		saveFileReportService.saveProcessOrderListToFile(receiver);
		
	}

}
