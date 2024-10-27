package com.evaluation.source.repository;

import com.evaluation.source.model.CarRepair;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepairRepository extends JpaRepository<CarRepair, Integer>{
  Optional<CarRepair> findByNameRepair(String repairName);
} 