package com.hrp.feedback.microservice.review.infrastructure.outputports.restapi;

public interface CheckClientExistsOutputPort {
    public boolean checkClientExists(String username);
}
