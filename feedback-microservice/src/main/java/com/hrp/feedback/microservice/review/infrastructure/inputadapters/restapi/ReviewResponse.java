package com.hrp.feedback.microservice.review.infrastructure.inputadapters.restapi;

import lombok.Value;

@Value
public class ReviewResponse {
    String message;
    public static ReviewResponse of(String message) {
        return new ReviewResponse(message);
    }
}
