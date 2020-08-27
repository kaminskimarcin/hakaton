package com.mmm.scanner.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessOrderReceiverItemDTO {
	private Long id;
	private String description;
	private int quantity;
	private int batch;
	private String status;
}
