package com.nimesa.assignment.controllers;

import com.nimesa.assignment.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;
}
