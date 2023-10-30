package com.nimesa.assignment.services;

import com.nimesa.assignment.enums.Status;
import com.nimesa.assignment.models.entities.Job;
import org.springframework.http.ResponseEntity;

public interface JobService {
    ResponseEntity<?> GetJobResult(String jobId);

    void saveJob(Job job);

    Job createAndGetJob(Status status);
}
