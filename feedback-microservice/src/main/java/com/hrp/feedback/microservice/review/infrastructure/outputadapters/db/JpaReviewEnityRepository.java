package com.hrp.feedback.microservice.review.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReviewEnityRepository extends JpaRepository<ReviewEntity, String> {

}
