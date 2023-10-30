package com.nimesa.assignment.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.nimesa.assignment.enums.Status;
import com.nimesa.assignment.exceptions.EntityNotFoundException;
import com.nimesa.assignment.exceptions.MissingRequiredParamException;
import com.nimesa.assignment.models.entities.Job;
import com.nimesa.assignment.models.entities.S3Bucket;
import com.nimesa.assignment.models.entities.S3Object;
import com.nimesa.assignment.models.dtos.ApiResponse;
import com.nimesa.assignment.repositories.BucketRepo;
import com.nimesa.assignment.repositories.S3ObjectRepo;
import com.nimesa.assignment.services.BucketService;
import com.nimesa.assignment.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepo bucketRepo;
    private final AmazonS3 amazonS3;
    private final JobService jobService;
    private final S3ObjectRepo s3ObjectRepo;

    @Override
    public boolean discoverAndSaveBuckets() {
        try {
            amazonS3.listBuckets().parallelStream().map(bucket -> {
                return S3Bucket.builder()
                        .id(UUID.randomUUID().toString())
                        .name(bucket.getName().toLowerCase())
                        .build();
            }).forEach(bucket -> {
                bucketRepo.save(bucket);
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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

    @Override
    public ResponseEntity<?> getS3BucketObjects(String bucketName) {
        bucketName = Optional.ofNullable(bucketName)
                .map(String::trim).map(String::toLowerCase)
                .orElseThrow(() -> {
                    throw new MissingRequiredParamException("bucketName");
                });
        S3Bucket bucket = bucketRepo.findByName(bucketName);
        if(null == bucket) throw new EntityNotFoundException("bucket",bucketName);
        Job job = jobService.createAndGetJob(Status.in_process);
        new Thread(() -> {
            AtomicInteger count = new AtomicInteger(0);
            ListObjectsRequest request = new ListObjectsRequest()
                    .withBucketName(bucket.getName());
                amazonS3.listObjects(request).getObjectSummaries().parallelStream()
                        .map(s3ObjectSummary -> S3Object.builder()
                                .bucketId(bucket.getId())
                                .bucketName(bucket.getName())
                                .id(UUID.randomUUID().toString())
                                .eTag(s3ObjectSummary.getETag())
                                .size(s3ObjectSummary.getSize())
                                .objectKey(s3ObjectSummary.getKey())
                                .storageClass(s3ObjectSummary.getStorageClass())
                                .build())
                        .forEach(s3Object -> {
                            count.incrementAndGet();
                            s3ObjectRepo.save(s3Object);
                        });
                bucket.setObjectCount(bucket.getObjectCount() + count.get());
                bucketRepo.save(bucket);
                job.setStatus(Status.success);
                jobService.saveJob(job);
        }).start();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(job)
                        .message("submitted request to fetch all bucket objects")
                        .build());
    }

    @Override
    public ResponseEntity<?> getS3BucketObjectsCount(String bucketName) {
        bucketName = Optional.ofNullable(bucketName)
                .map(String::trim).map(String::toLowerCase)
                .orElseThrow(() -> {
                    throw new MissingRequiredParamException("bucketName");
                });
        S3Bucket bucket = bucketRepo.findByName(bucketName);
        if(null == bucket) throw new EntityNotFoundException("bucket",bucketName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(bucket.getObjectCount())
                        .build());
    }

    @Override
    public ResponseEntity<?> getS3BucketObjectlike(String bucketName, String pattern) {
        List<S3Object> objects = s3ObjectRepo.getS3BucketObjectLike(bucketName,pattern);
        List<String> fileNames = objects.stream().map(S3Object::getObjectKey).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(fileNames)
                        .build());
    }
}
