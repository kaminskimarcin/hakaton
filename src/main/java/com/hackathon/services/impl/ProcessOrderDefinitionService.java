package com.hackathon.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.entities.ProcessOrderDefinition;
import com.hackathon.repository.ProcessOrderDefinitionRepository;
import com.hackathon.services.IProcessOrderDefinitionService;

@Service
public class ProcessOrderDefinitionService implements IProcessOrderDefinitionService {

	@Autowired 
	private ProcessOrderDefinitionRepository repository;

	@Override
	public Optional<ProcessOrderDefinition> findById(Long id) {
		return repository.findById(id);
	}
	
	
}
