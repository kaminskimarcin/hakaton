package com.mmm.hackathon.process.repository;

import com.mmm.hackathon.process.domain.entities.ProcessOrderReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderReceiverRepository extends JpaRepository<ProcessOrderReceiver, Long> {

}
