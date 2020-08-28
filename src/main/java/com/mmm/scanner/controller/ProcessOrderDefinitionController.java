package com.mmm.scanner.controller;

import com.mmm.scanner.domain.entities.ProcessOrderItem;
import com.mmm.scanner.services.impl.ProcessOrderDefinitionService;
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
    private final ProcessOrderDefinitionService processOrderDefService;

    @GetMapping("/itemsListForProcess")
    public List<ProcessOrderItem> getListOfRequiredItems(@RequestParam(required = false) long orderID) {
        return processOrderDefService.findProcessOrderItemsById(orderID);
    }
}
