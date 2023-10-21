package com.nimesa.assignment.services;

import org.springframework.http.ResponseEntity;

public interface BucketService {
    public boolean discoverAndSaveBuckets();

    ResponseEntity<?> getBuckets();

    ResponseEntity<?> getS3BucketObjects(String bucketName);

    ResponseEntity<?> getS3BucketObjectsCount(String bucketName);

    ResponseEntity<?> getS3BucketObjectlike(String bucketName, String pattern);
}
