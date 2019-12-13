package com.hackathon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.entities.ProcessOrderDefinition;

@Repository
public interface ProcessOrderDefinitionRepository extends CrudRepository<ProcessOrderDefinition, Long> {

}
