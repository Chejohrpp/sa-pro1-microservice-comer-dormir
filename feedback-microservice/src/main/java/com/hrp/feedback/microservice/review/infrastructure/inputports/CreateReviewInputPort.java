package com.hrp.feedback.microservice.review.infrastructure.inputports;

import com.hrp.feedback.microservice.review.application.createreview.CreateReviewRequest;
import jakarta.persistence.EntityNotFoundException;

public interface CreateReviewInputPort {
    void RegisterReview(CreateReviewRequest createReviewRequest) throws IllegalArgumentException, EntityNotFoundException;
}
