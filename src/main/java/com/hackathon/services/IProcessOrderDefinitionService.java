package com.hackathon.services;

import java.util.Optional;

import com.hackathon.entities.ProcessOrderDefinition;

public interface IProcessOrderDefinitionService {
	Optional<ProcessOrderDefinition>findById(Long id);

}
