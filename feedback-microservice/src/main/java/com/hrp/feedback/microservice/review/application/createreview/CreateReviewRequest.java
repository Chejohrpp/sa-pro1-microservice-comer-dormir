package com.hrp.feedback.microservice.review.application.createreview;

import com.hrp.feedback.microservice.review.domain.Review;
import com.hrp.feedback.microservice.review.domain.ReviewEstablishment;
import lombok.Value;

@Value
public class CreateReviewRequest {
    private String username;
    private int rating;
    private String comment;
    private ReviewEstablishment reviewEstablishment;
    private String establishmentId;

    public Review toDomain() {
        return Review.builder()
                .username(username)
                .rating(rating)
                .comment(comment)
                .reviewEstablishment(reviewEstablishment)
                .establishment(establishmentId)
                .build();
    }
}
