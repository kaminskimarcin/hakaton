package com.hackathon.process.services;

import com.hackathon.process.domain.entities.ProcessOrderItem;

import java.util.List;

public interface IProcessOrderDefinitionService {
    List<ProcessOrderItem> findProcessOrderItemsById(Long id);
}
