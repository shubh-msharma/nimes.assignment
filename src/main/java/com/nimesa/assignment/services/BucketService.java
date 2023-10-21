package com.nimesa.assignment.services;

import org.springframework.http.ResponseEntity;

public interface BucketService {
    public boolean discoverAndSaveBuckets();

    ResponseEntity<?> getBuckets();
}
