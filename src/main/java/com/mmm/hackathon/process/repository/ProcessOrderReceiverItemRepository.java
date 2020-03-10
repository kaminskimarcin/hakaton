package com.mmm.hackathon.process.repository;

import com.mmm.hackathon.process.domain.entities.ProcessOrderReceiverItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessOrderReceiverItemRepository extends JpaRepository<ProcessOrderReceiverItem, Long> {

}
