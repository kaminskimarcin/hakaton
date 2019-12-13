package com.hackathon.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.hackathon.entities.ProcessOrderReceiver;
import com.hackathon.repositories.ProcessOrderReceiverRepository;

@Controller
public class ProcessOrderController {
	
	@Autowired
	ProcessOrderReceiverRepository orderRepo;
	
//	@PostMapping(value = "/", produces = "application/json")
//	@ResponseBody
	public String updateProcessOrder(@Valid ProcessOrderReceiver receiver, BindingResult result)
	{
		orderRepo.save(receiver);
		
		return "{\"success\":1}";
	}

}
