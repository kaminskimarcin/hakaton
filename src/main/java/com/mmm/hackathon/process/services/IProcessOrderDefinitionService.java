package com.mmm.hackathon.process.services;

import com.mmm.hackathon.process.domain.entities.ProcessOrderItem;

import java.util.List;

public interface IProcessOrderDefinitionService {
    List<ProcessOrderItem> findProcessOrderItemsById(Long id);
}
