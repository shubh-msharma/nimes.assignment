package com.nimesa.assignment.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface InstanceService {
    public boolean discoverAndSaveInstances();

    ResponseEntity<?> getInstances();
}
