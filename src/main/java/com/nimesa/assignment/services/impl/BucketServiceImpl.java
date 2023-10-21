package com.nimesa.assignment.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.nimesa.assignment.models.S3Bucket;
import com.nimesa.assignment.models.dtos.ApiResponse;
import com.nimesa.assignment.repositories.BucketRepo;
import com.nimesa.assignment.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepo bucketRepo;
    private final AmazonS3 amazonS3;

    @Override
    public boolean discoverAndSaveBuckets() {
        amazonS3.listBuckets().parallelStream().map(bucket -> {
            return S3Bucket.builder()
                    .name(bucket.getName())
                    .name(UUID.randomUUID().toString())
                    .build();
        }).forEach(bucket -> {
            bucketRepo.save(bucket);
        });
        return false;
    }

    @Override
    public ResponseEntity<?> getBuckets() {
        List<S3Bucket> bucketList = bucketRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(bucketList)
                        .build());
    }
}
