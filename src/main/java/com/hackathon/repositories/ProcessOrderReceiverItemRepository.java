package com.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.entities.ProcessOrderReceiver;

public interface ProcessOrderReceiverItemRepository extends JpaRepository<ProcessOrderReceiverItem,Long> {

}
