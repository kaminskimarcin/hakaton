package com.mmm.cuttingstock.repository;

import com.mmm.cuttingstock.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase, Integer> {
}
