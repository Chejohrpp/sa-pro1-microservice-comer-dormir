package com.hrp.feedback.microservice.review.infrastructure.outputports.restapi;

public interface CheckHotelExistsOutputPort {
    public boolean checkHotelExists(String hotelId);
}
