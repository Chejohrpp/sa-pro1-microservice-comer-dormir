package com.hrp.order.microservice.order.infrastructure.outputadapters.restapi;

import com.hrp.order.microservice.order.infrastructure.outputports.restapi.CheckMenuDishRestaurantOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.restapi.CheckUsernameExistsOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.restapi.GetDishPriceOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class OrderRestApiOutputAdapter implements CheckMenuDishRestaurantOutputPort, CheckUsernameExistsOutputPort, GetDishPriceOutputPort {
    private static final String URL_RESTAURANT = "http://localhost:8084/v1/restaurants/";
    private static final String URL_USERS = "http://localhost:8081/v1/users/";

    @Override
    public boolean existsDishInRestaurant(String restaurantId, Long dishId) {
        RestClient restClient = RestClient.create();
        String url = URL_RESTAURANT+"existing-menu?restaurantid="+restaurantId+"&dishid="+dishId;
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
    public boolean checkUserExists(String username) {
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
    public double getPriceDish(Long dishId) {
        RestClient restClient = RestClient.create();
        try{
            String url = URL_RESTAURANT + "/dish/" + dishId + "/price";
            ResponseEntity<Double> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(Double.class); // Send a GET request and map response to Double
            return response.getBody() != null ? response.getBody() : 0.0;
        }catch (RestClientResponseException e) {
            throw new IllegalArgumentException("something wrong getting the price for the dish");
        }
    }
}
