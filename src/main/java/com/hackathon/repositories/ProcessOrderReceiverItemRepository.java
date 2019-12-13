package com.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.entities.ProcessOrderReceiverItem;

public interface ProcessOrderReceiverItemRepository extends JpaRepository<ProcessOrderReceiverItem,Long> {

}
