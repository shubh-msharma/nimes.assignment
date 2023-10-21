package com.nimesa.assignment.services;

import com.nimesa.assignment.models.Job;
import org.springframework.http.ResponseEntity;

public interface JobService {
    ResponseEntity<?> GetJobResult(String jobId);

    void saveJob(Job job);

    Job createAndGetJob();
}
