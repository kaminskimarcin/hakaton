package com.mmm.scanner.services;

import com.mmm.scanner.domain.entities.ProcessOrderItem;

import java.util.List;

public interface IProcessOrderDefinitionService {
    List<ProcessOrderItem> findProcessOrderItemsById(Long id);
}
