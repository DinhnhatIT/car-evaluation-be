package com.evaluation.source.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.source.base.BaseController;
import com.evaluation.source.dto.base.ResultResponse;
import com.evaluation.source.model.Feedback;
import com.evaluation.source.repository.FeedbackRepository;

import lombok.*;

@RestController
@RequiredArgsConstructor
public class FeedbackController extends BaseController{
    private final FeedbackRepository feedbackRepository;

    @PostMapping("/feedback")
    public ResponseEntity<ResultResponse> create(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);
        return buildResponse("Feedback successfully");
    }

    @GetMapping("/feedback")
    public ResponseEntity<ResultResponse> get() {
        return buildResponse("List Feedback", feedbackRepository.findAll());
    }

}
