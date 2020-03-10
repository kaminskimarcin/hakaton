package com.mmm.hackathon.process.controller;

import com.mmm.hackathon.process.domain.entities.ProcessOrderItem;
import com.mmm.hackathon.process.services.impl.ProcessOrderDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProcessOrderDefinitionController {
    private ProcessOrderDefinitionService processOrderDefService;

    @GetMapping("/getItemsListForProcess")
    public List<ProcessOrderItem> getListOfRequiredItems(@RequestParam(required = false) long orderID) {
        return processOrderDefService.findProcessOrderItemsById(orderID);
    }
}
