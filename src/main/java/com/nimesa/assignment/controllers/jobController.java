package com.nimesa.assignment.controllers;

import com.nimesa.assignment.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class jobController {

    private final JobService jobService;

    @GetMapping("/jobs/{jobId}")
    ResponseEntity<?> GetJobResult(
            @PathVariable("jobId") String jobId
    ){
        return jobService.GetJobResult(jobId);
    }
}
