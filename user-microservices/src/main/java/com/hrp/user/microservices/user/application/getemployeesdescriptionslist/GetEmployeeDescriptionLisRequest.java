package com.hrp.user.microservices.user.application.getemployeesdescriptionslist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeDescriptionLisRequest {
    private List<String> usernames;
}
