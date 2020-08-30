package com.mmm.cuttingstock.controller;

import com.mmm.cuttingstock.dto.OrderDto;
import com.mmm.cuttingstock.service.CuttingStockService;
import com.mmm.cuttingstock.dto.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class CuttingStockController {
	private final CuttingStockService cuttingStockService;

	@PostMapping(value = "/generate")
	private OrderResponse generate(@RequestBody OrderDto order) throws IOException {
		return cuttingStockService.calculate(order);
	}

}
