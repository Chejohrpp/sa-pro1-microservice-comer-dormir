package com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputadapters.restapi;

import com.hrp.reports.microservice.common.enums.Establishment;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputadapters.restapi.employeebyestablishment.EmployeeByEstablishmentResponse;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports.GenerateEmployeeEstablishmentPdfInputPort;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports.GetReportEmployeeEstablishmentInputPort;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("v1/reports/")
public class ReportsController {
    private final GetReportEmployeeEstablishmentInputPort getReportEmployeeEstablishmentInputPort;
    private final GenerateEmployeeEstablishmentPdfInputPort generateEmployeeEstablishmentPdfInputPort;

    @Autowired
    public ReportsController(GetReportEmployeeEstablishmentInputPort getReportEmployeeEstablishmentInputPort, GenerateEmployeeEstablishmentPdfInputPort generateEmployeeEstablishmentPdfInputPort) {
        this.getReportEmployeeEstablishmentInputPort = getReportEmployeeEstablishmentInputPort;
        this.generateEmployeeEstablishmentPdfInputPort = generateEmployeeEstablishmentPdfInputPort;
    }


    @GetMapping("get-employee-by-establishment/{type}")
    public ResponseEntity<EmployeeByEstablishmentResponse> employeeByEstablishment(
            @PathVariable Establishment type,
            @RequestParam("establishment") String establisment
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(getReportEmployeeEstablishmentInputPort.getReportEmployeeEstablishment(establisment, type));
    }

    @PostMapping("get-employee-by-establishment-pdf/{type}/{establisment}")
    public ResponseEntity<byte[]> generateEmployeePdf
            (
                    @PathVariable Establishment type,
                    @PathVariable String establisment
            ) {

        EmployeeByEstablishmentResponse response = getReportEmployeeEstablishmentInputPort.getReportEmployeeEstablishment(establisment, type);

        try {
            byte[] pdfBytes = generateEmployeeEstablishmentPdfInputPort.generateEmployeeReport(type.toString()+ " " + establisment, response.getEmployees());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=employee_report.pdf");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(pdfBytes);

        } catch (DocumentException | IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


}
