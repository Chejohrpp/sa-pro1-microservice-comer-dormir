package com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputadapters.restapi;

import com.hrp.reports.microservice.common.enums.Establishment;
import com.hrp.reports.microservice.employeebyestablishment.application.getreport.GetEmployeeDescriptionLisRequest;
import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi.GetDescriptonsEmployeeByUsernameOutputPort;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi.GetUsernamesEmployeeByEstablishmentOutputPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Component
public class ReportsRestAPIOuputAdatepter implements GetUsernamesEmployeeByEstablishmentOutputPort, GetDescriptonsEmployeeByUsernameOutputPort {
    private static final String URL_PAYROLLS = "http://localhost:8089/v1/payrolls/";
    private static final String URL_USERS = "http://localhost:8081/v1/users/";


    @Override
    public List<String> getUsernamesEmployeeByEstablishment(String establishmentId, Establishment type) {
        RestTemplate restTemplate = new RestTemplate();
        // Definir la URL base
        String url = URL_PAYROLLS +"reports/employees/" + establishmentId + "/" + type;

        // Construir la URI final
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        URI finalUri = uriBuilder.build().toUri();

        try {
            // Hacer la solicitud GET a la URL y capturar la respuesta
            ResponseEntity<GetEmployeeDescriptionLisRequest> response = restTemplate.exchange(
                    finalUri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<GetEmployeeDescriptionLisRequest>() {}
            );

            // Verificar si el estado de la respuesta es OK (200)
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().getUsernames(); // Devolver los usernames si todo va bien
            } else {
                return Collections.emptyList(); // Retornar lista vacía si no hay resultados
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Collections.emptyList(); // Retornar lista vacía si no se encuentra nada
            } else {
                throw e; // Si es otro error, lanzar la excepción
            }
        }
    }

    @Override
    public List<Employee> getDescriptonsEmployeeByUsername(List<String> usernames) {
        RestTemplate restTemplate = new RestTemplate();
        // Crear el request body con los usernames
        GetEmployeeDescriptionLisRequest requestBody = new GetEmployeeDescriptionLisRequest(usernames);

        // Definir los headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Crear la entidad Http
        HttpEntity<GetEmployeeDescriptionLisRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        // Definir la URL de la API
        String url = URL_USERS + "reports/get-description-employees-list";

        // Hacer la solicitud POST y obtener la respuesta
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Employee>>() {
                });

        // Devolver la lista de Employee obtenida de la respuesta
        return response.getBody();
    }
}
