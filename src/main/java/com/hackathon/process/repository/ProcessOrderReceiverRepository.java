package com.hackathon.process.repository;

import com.hackathon.process.domain.entities.ProcessOrderReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderReceiverRepository extends JpaRepository<ProcessOrderReceiver, Long> {

}