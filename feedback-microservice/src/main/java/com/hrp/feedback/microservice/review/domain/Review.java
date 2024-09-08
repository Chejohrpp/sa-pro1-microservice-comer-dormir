package com.hrp.feedback.microservice.review.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Review {
    private UUID id;
    private String username;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
    private ReviewEstablishment reviewEstablishment;
    private String establishment;

    public boolean validateRating(){
        return rating > 0 && rating < 6;
    }
}
