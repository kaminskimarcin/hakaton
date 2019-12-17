package com.hackathon.process.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderReceiverDTO {
    private Long orderId;
    private List<ProcessOrderReceiverItemDTO> items;
}
