package com.evaluation.source.repository;

import com.evaluation.source.model.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
    
    @Query("SELECT c from Car c WHERE (:model IS NULL OR c.model.id = :model) AND (:version IS NULL OR c.version = :version) AND (:year IS NULL OR c.year = :year) AND (:color IS NULL OR c.color = :color)")
    List<Car> findCar(int model, String version, int year, String color);
}
