package com.hrp.feedback.microservice.review.infrastructure.outputports.db;

import com.hrp.feedback.microservice.review.domain.Review;

public interface CreateReviewOutputPort {
    Review save(Review review);
}
