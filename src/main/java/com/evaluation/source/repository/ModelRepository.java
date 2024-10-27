package com.evaluation.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.source.model.Brand;
import com.evaluation.source.model.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{
    
    Optional<Model> findById(int id);

    List<Model> findByBrand(Brand brand);

    List<Model> findByName(String name);
}
