package com.nimesa.assignment.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AwsService {
    public ResponseEntity<?> discoverServices(List<String> services);

    ResponseEntity<?> getDiscoveryResult(String service);
}
