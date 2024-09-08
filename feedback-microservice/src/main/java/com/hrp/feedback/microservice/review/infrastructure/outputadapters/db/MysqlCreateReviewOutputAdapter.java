package com.hrp.feedback.microservice.review.infrastructure.outputadapters.db;

import com.hrp.feedback.microservice.common.annotation.PersistenceAdapter;
import com.hrp.feedback.microservice.review.domain.Review;
import com.hrp.feedback.microservice.review.infrastructure.outputports.db.CreateReviewOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceAdapter
public class MysqlCreateReviewOutputAdapter implements CreateReviewOutputPort {
    private final JpaReviewEnityRepository entityRepository;

    @Autowired
    public MysqlCreateReviewOutputAdapter(JpaReviewEnityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Review save(Review review) {
        ReviewEntity reviewEntity =  ReviewEntity.from(review);
        return entityRepository.save(reviewEntity)
                .toDomain();
    }
}
