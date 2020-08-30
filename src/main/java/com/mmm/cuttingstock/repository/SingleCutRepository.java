package com.mmm.cuttingstock.repository;

import com.mmm.cuttingstock.model.SingleCut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleCutRepository extends JpaRepository<SingleCut, Integer> {
}
