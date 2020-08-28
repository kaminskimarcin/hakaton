package com.mmm.scanner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mmm.scanner.domain.entities.ProcessOrderDefinition;

@Repository
public interface ProcessOrderDefinitionRepository extends CrudRepository<ProcessOrderDefinition, Long> {

}
