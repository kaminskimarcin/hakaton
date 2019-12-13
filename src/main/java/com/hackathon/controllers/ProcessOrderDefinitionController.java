package com.hackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.entities.ProcessOrderDefinition;
import com.hackathon.services.impl.ProcessOrderDefinitionService;

@Controller
public class ProcessOrderDefinitionController {

	@Autowired
    private ProcessOrderDefinitionService processOrderDefService;

	
	
	@GetMapping("/getRequiredItems")
	@ResponseBody
	public ProcessOrderDefinition getListOfRequiredItems(@RequestParam(required = false) long orderID) {
		
		return processOrderDefService.findById(orderID).get();
	}
}
