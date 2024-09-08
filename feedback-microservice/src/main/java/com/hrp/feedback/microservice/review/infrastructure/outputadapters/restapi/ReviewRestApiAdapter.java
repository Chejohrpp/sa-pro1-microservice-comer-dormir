package com.hrp.feedback.microservice.review.infrastructure.outputadapters.restapi;

import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckClientExistsOutputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckHotelExistsOutputPort;
import com.hrp.feedback.microservice.review.infrastructure.outputports.restapi.CheckRestaurantExistsOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class ReviewRestApiAdapter implements CheckHotelExistsOutputPort, CheckRestaurantExistsOutputPort, CheckClientExistsOutputPort {
    private static final String URL_RESTAURANTS = "http://localhost:8084/v1/restaurants/";
    private static final String URL_HOTELS = "http://localhost:8082/v1/hotels/";
    private static final String URL_USERS = "http://localhost:8081/v1/users/";
    @Override
    public boolean checkClientExists(String username) {
        RestClient restClient = RestClient.create();
        String url = URL_USERS + "exists/client?username="+username;
        try {
            restClient.head()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientResponseException e) {
            if(e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                return false;
            } else {
                //handle trow if not already there
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean checkHotelExists(String hotelId) {
        RestClient restClient = RestClient.create();
        String url = URL_HOTELS + "existing-hotel?hotelid="+hotelId;
        try {
            restClient.head()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientResponseException e) {
            if(e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                return false;
            } else {
                //handle trow if not already there
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean exists(String restaurantId) {
        RestClient restClient = RestClient.create();
        String url = URL_RESTAURANTS + "existing-restaurant?restaurantid="+restaurantId;
        try {
            restClient.head()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientResponseException e) {
            if(e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                return false;
            } else {
                //handle trow if not already there
                e.printStackTrace();
            }
        }
        return false;
    }
}
