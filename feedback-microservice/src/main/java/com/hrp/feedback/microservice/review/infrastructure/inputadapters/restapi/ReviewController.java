package com.hrp.feedback.microservice.review.infrastructure.inputadapters.restapi;

import com.hrp.feedback.microservice.review.application.createreview.CreateReviewRequest;
import com.hrp.feedback.microservice.review.infrastructure.inputports.CreateReviewInputPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/reviews")
public class ReviewController {
    private final CreateReviewInputPort createReviewInputPort;

    @Autowired
    public ReviewController(CreateReviewInputPort createReviewInputPort) {
        this.createReviewInputPort = createReviewInputPort;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> registerReview(@RequestBody CreateReviewRequest createReviewRequest) throws IllegalArgumentException, EntityNotFoundException{
        createReviewInputPort.RegisterReview(createReviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReviewResponse.of("the review has been registered successfully"));
    }
}
