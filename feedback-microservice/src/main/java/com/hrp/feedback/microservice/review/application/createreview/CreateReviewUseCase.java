package com.hrp.feedback.microservice.review.application.createreview;

import com.hrp.feedback.microservice.common.annotation.UseCase;
import com.hrp.feedback.microservice.review.domain.Review;
import com.hrp.feedback.microservice.review.domain.ReviewEstablishment;
import com.hrp.feedback.microservice.review.infrastructure.inputports.CreateReviewInputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.db.CreateReviewOutputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckClientExistsOutputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckHotelExistsOutputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckRestaurantExistsOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UseCase
@Transactional
public class CreateReviewUseCase implements CreateReviewInputPort {
    private final CreateReviewOutputPort createReviewOutputPort;
    private final CheckRestaurantExistsOutputPort checkRestaurantExistsOutputPort;
    private final CheckHotelExistsOutputPort checkHotelExistsOutputPort;
    private final CheckClientExistsOutputPort clientExistsOutputPort;

    @Autowired
    public CreateReviewUseCase(CreateReviewOutputPort createReviewOutputPort, CheckRestaurantExistsOutputPort checkRestaurantExistsOutputPort, CheckHotelExistsOutputPort checkHotelExistsOutputPort, CheckClientExistsOutputPort clientExistsOutputPort) {
        this.createReviewOutputPort = createReviewOutputPort;
        this.checkRestaurantExistsOutputPort = checkRestaurantExistsOutputPort;
        this.checkHotelExistsOutputPort = checkHotelExistsOutputPort;
        this.clientExistsOutputPort = clientExistsOutputPort;
    }

    @Override
    public void RegisterReview(CreateReviewRequest createReviewRequest) throws IllegalArgumentException, EntityNotFoundException {
        //convert to domain
        Review review =  createReviewRequest.toDomain();
        //validate the rating
        if(!review.validateRating()){
            throw new IllegalArgumentException("Invalid rating");
        }
        //verify the establishment
        //validate if the establishment exists
        if(review.getReviewEstablishment().equals(ReviewEstablishment.HOTEL)){
            if(!checkHotelExistsOutputPort.checkHotelExists(review.getEstablishment())){
                throw new IllegalArgumentException("Hotel does not exist");
            }
        } else {
            if(!checkRestaurantExistsOutputPort.exists(review.getEstablishment())){
                throw new IllegalArgumentException("Restaurant does not exist");
            }
        }

        //validate if the client exists
        if(!clientExistsOutputPort.checkClientExists(review.getUsername())){
            throw new IllegalArgumentException("Client does not exist");
        }
        //complete the data
        review.setReviewDate(LocalDate.now());
        //save
        review = createReviewOutputPort.save(review);
    }
}
