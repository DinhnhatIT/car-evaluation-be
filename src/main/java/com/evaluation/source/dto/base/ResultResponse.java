package com.evaluation.source.dto.base;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ResultResponse(
        int status,
        String message,
        Instant timestamp,
        Object result
) {
}
