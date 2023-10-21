package com.nimesa.assignment.controllers;

import com.nimesa.assignment.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;

    @GetMapping("/bucket/objects")
    public ResponseEntity<?> getS3BucketObjects(
            @RequestParam("bucketName") String bucketName){
        return bucketService.getS3BucketObjects(bucketName);
    }

    @GetMapping("/bucket/objects/count")
    public ResponseEntity<?> getS3BucketObjectsCount(
            @RequestParam("bucketName") String bucketName){
        return bucketService.getS3BucketObjectsCount(bucketName);
    }

    @GetMapping("/bucket/objects/search")
    public ResponseEntity<?> getS3BucketObjectlike(
            @RequestParam("BucketName") String bucketName,
            @RequestParam("Pattern") String pattern){
        return bucketService.getS3BucketObjectlike(bucketName,pattern);
    }
}
