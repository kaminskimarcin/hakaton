package com.mmm.scanner.repository;

import com.mmm.scanner.domain.entities.ProcessOrderReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderReceiverRepository extends JpaRepository<ProcessOrderReceiver, Long> {

}
