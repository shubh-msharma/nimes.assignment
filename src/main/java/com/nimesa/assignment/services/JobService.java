package com.nimesa.assignment.services;

import org.springframework.http.ResponseEntity;

public interface JobService {
    ResponseEntity<?> GetJobResult(String jobId);
}
