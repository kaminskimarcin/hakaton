package com.hackathon.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.entities.ProcessOrderReceiver;
import com.hackathon.entities.ProcessOrderReceiverItem;
import com.hackathon.repositories.ProcessOrderReceiverRepository;

@Controller
public class ProcessOrderController {
	
	@Autowired
	ProcessOrderReceiverRepository orderRepo;
	
	@RequestMapping(value = "/test" , method= RequestMethod.POST, produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public void updateProcessOrder(@RequestBody ProcessOrderReceiver receiver)
	{
		orderRepo.saveAll(receiver.getItems());
		orderRepo.save(receiver);
		
	}

}
