package com.hackathon.process.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.process.domain.entities.ProcessOrderDefinition;

@Repository
public interface ProcessOrderDefinitionRepository extends CrudRepository<ProcessOrderDefinition, Long> {

}
