package com.mmm.scanner.repository;

import com.mmm.scanner.domain.entities.ProcessOrderReceiverItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderReceiverItemRepository extends JpaRepository<ProcessOrderReceiverItem, Long> {

}
