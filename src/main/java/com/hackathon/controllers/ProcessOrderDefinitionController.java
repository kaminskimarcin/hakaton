package com.hackathon.controllers;

import com.hackathon.entities.ProcessOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hackathon.services.impl.ProcessOrderDefinitionService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProcessOrderDefinitionController {

	@Autowired
    private ProcessOrderDefinitionService processOrderDefService;

	
	
	@GetMapping("/getItemsListForProcess")
	@ResponseBody
	public List<ProcessOrderItem> getListOfRequiredItems(@RequestParam(required = false) long orderID) {
		
		return processOrderDefService.findById(orderID).get().getItems();
	}
}
