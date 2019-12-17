package com.hackathon.process.controller;


import com.hackathon.process.domain.dto.ProcessOrderReceiverDTO;
import com.hackathon.process.services.impl.ProcessOrderDefinitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProcessOrderController {
    private final ProcessOrderDefinitionService processOrderDefinitionService;

    @RequestMapping(value = "/submitCompletedItemsListForProcess", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateProcessOrder(@RequestBody ProcessOrderReceiverDTO receiverDto) {
        processOrderDefinitionService.updateProcessOrder(receiverDto);
        processOrderDefinitionService.generateReport(receiverDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
