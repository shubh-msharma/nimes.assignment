package com.nimesa.assignment.controllers;

import com.nimesa.assignment.services.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AwsServicesController {

    private final AwsService awsService;

    @PostMapping("/services")
    public ResponseEntity<?> discoverServices(
            @RequestBody List<String> services
    ){
        return awsService.discoverServices(services);
    }

    @GetMapping("/services")
    public ResponseEntity<?> getDiscoveryResult(
            @RequestParam("Service") String service
    ){
        return awsService.getDiscoveryResult(service);
    }

}
