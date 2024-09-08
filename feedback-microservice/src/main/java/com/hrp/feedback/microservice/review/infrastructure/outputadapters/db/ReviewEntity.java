package com.hrp.feedback.microservice.review.infrastructure.outputadapters.db;

import com.hrp.feedback.microservice.review.domain.Review;
import com.hrp.feedback.microservice.review.domain.ReviewEstablishment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
public class ReviewEntity {
    @Id
    private String id;
    @Column
    private String username;
    @Column
    private int rating;
    @Column
    private String comment;
    @Column
    private LocalDate reviewDate;
    @Column
    @Enumerated(EnumType.STRING)
    private ReviewEstablishment reviewEstablishment;
    @Column
    private String establishment;

    public ReviewEntity(String id, String username, int rating, String comment, LocalDate reviewDate, ReviewEstablishment reviewEstablishment, String establishment) {
        this.id = id;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.reviewEstablishment = reviewEstablishment;
        this.establishment = establishment;
    }

    public static ReviewEntity from(Review review) {
        return new ReviewEntity(
                UUID.randomUUID().toString(),
                review.getUsername(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate(),
                review.getReviewEstablishment(),
                review.getEstablishment()
        );
    }

    public Review toDomain(){
        return Review.builder()
                .id(UUID.fromString(id))
                .username(username)
                .rating(rating)
                .comment(comment)
                .reviewDate(reviewDate)
                .reviewEstablishment(reviewEstablishment)
                .establishment(establishment).build();
    }
}
