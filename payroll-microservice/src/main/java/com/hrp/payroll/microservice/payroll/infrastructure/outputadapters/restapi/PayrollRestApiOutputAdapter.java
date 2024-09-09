package com.hrp.payroll.microservice.payroll.infrastructure.outputadapters.restapi;

import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckEmployeeExistsOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckHotelExistsOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckRestaurantExistsOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.GetSalaryEmployeeOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class PayrollRestApiOutputAdapter implements CheckHotelExistsOutputPort, CheckEmployeeExistsOutputPort, CheckRestaurantExistsOutputPort, GetSalaryEmployeeOutputPort {
//    private static final String URL_RESTAURANTS = "http://localhost:8084/v1/restaurants/";
//    private static final String URL_HOTELS = "http://localhost:8082/v1/hotels/";
//    private static final String URL_USERS = "http://localhost:8081/v1/users/";

    private static final String URL_RESTAURANTS = "http://restaurant-microservice:8080/v1/restaurants/";
    private static final String URL_HOTELS = "http://hotel-microservice:8080/v1/hotels/";
    private static final String URL_USERS = "http://user-microservice:8080/v1/users/";

    @Override
    public boolean checkEmployeeExists(String username) {
        RestClient restClient = RestClient.create();
        String url = URL_USERS + "exists/employee?username="+username;
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

    @Override
    public double getSalary(String username) {
        RestClient restClient = RestClient.create();
        try{
            String url = URL_USERS + "/employee/" + username + "/salary";
            ResponseEntity<Double> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(Double.class); // Send a GET request and map response to Double
            return response.getBody() != null ? response.getBody() : 0.0;
        }catch (RestClientResponseException e) {
            throw new IllegalArgumentException("something wrong getting the employee salary");
        }
    }
}
