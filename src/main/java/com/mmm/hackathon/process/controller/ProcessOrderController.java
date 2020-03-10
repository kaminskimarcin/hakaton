package com.mmm.hackathon.process.controller;


import com.mmm.hackathon.process.domain.dto.ProcessOrderReceiverDTO;
import com.mmm.hackathon.process.services.impl.ProcessOrderDefinitionService;
import com.mmm.hackathon.process.domain.entities.ProcessOrderReceiver;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProcessOrderController {
    private final ProcessOrderDefinitionService processOrderDefinitionService;

    @PostMapping(value = "/submitCompletedItemsListForProcess", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateProcessOrder(@RequestBody ProcessOrderReceiverDTO receiverDto) {
        processOrderDefinitionService.updateProcessOrder(receiverDto);
        processOrderDefinitionService.generateReport(receiverDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/generateReport", produces = MediaType.APPLICATION_PDF_VALUE, consumes = "application/json")
    public ResponseEntity<?> generateReport(@RequestBody ProcessOrderReceiverDTO receiverDto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/allAvailableCheckedProcess")
    private List<ProcessOrderReceiver> getAllAvailableCheckedProcess() {
        return processOrderDefinitionService.getAllAvailableCheckedProcess();
    }

}
