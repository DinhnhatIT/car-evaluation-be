package com.evaluation.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluation.source.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
    
}
