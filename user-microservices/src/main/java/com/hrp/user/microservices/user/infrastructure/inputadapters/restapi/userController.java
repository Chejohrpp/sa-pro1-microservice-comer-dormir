package com.hrp.user.microservices.user.infrastructure.inputadapters.restapi;

import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.user.application.createclient.CreateClientRequest;
import com.hrp.user.microservices.user.application.createemployee.CreateEmployeeRequest;
import com.hrp.user.microservices.user.infrastructure.inputports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users/")
public class userController {
    private final CreateClientInputPort createClientInputPort;
    private final CreateEmployeeInputPort createEmployeeInputPort;
    private final FindClientInputPort findClientInputPort;
    private final FindEmployeeInputPort findEmployeeInputPort;
    private final GetSalaryEmployeeInuputPort getSalaryEmployeeInuputPort;

    @Autowired
    public userController(CreateClientInputPort createUserInputPort, CreateEmployeeInputPort createEmployeeInputPort, FindClientInputPort findClientInputPort, FindEmployeeInputPort findEmployeeInputPort, GetSalaryEmployeeInuputPort getSalaryEmployeeInuputPort) {
        this.createClientInputPort = createUserInputPort;
        this.createEmployeeInputPort = createEmployeeInputPort;
        this.findClientInputPort = findClientInputPort;
        this.findEmployeeInputPort = findEmployeeInputPort;
        this.getSalaryEmployeeInuputPort = getSalaryEmployeeInuputPort;
    }

    @PostMapping("create/client")
    public ResponseEntity<UserResponse> createClient(@RequestBody CreateClientRequest createClientRequest) throws AlreadyExistsException {
        createClientInputPort.createClient(createClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of("Client created successfully"));
    }

    @PostMapping("create/employee")
    public ResponseEntity<UserResponse> createEmployee(@RequestBody CreateEmployeeRequest createClientRequest) throws AlreadyExistsException {
        createEmployeeInputPort.createEmployee(createClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.of("Employee created successfully"));
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/exists/client")
    public ResponseEntity<Void> checkClientExists(@RequestParam("username") String username) {
        if(findClientInputPort.findClientByUsername(username).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/exists/employee")
    public ResponseEntity<Void> checkEmployeeExists(@RequestParam("username") String username) {
        if(findEmployeeInputPort.findEmployee(username).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/employee/{username}/salary")
    public ResponseEntity<Double> getSalaryEmployee(@PathVariable String username) {
        double salary = getSalaryEmployeeInuputPort.getSalaryEmployeeInuput(username);
        return ResponseEntity.status(HttpStatus.OK).body(salary);
    }
}
