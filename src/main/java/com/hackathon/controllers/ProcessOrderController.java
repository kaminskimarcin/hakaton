package com.hackathon.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.entities.ProcessOrder;

@Controller
public class ProcessOrderController {
	
//	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
//	@ResponseBody
	public List<ProcessOrder> getProcessOrder(/*@RequestBody*/ List<ProcessOrder> processOrder)
	{
		List<ProcessOrder> output = new ArrayList<>();
		int size = processOrder.size();
		ProcessOrder ps = new ProcessOrder();;
		for(int i = 0; i < size;i++) {
			ps.setIdComponent(processOrder.get(i).getIdComponent());
			ps.setDesc(processOrder.get(i).getDesc());
			ps.setQuantity(processOrder.get(i).getQuantity());
			ps.setBatch(processOrder.get(i).getBatch());
			output.add(ps);
		}
		
		
		return output;
	}

}
