package com.nimesa.assignment.services.impl;

import com.nimesa.assignment.enums.Status;
import com.nimesa.assignment.exceptions.EntityNotFoundException;
import com.nimesa.assignment.exceptions.MissingRequiredParamException;
import com.nimesa.assignment.models.Job;
import com.nimesa.assignment.models.dtos.ApiResponse;
import com.nimesa.assignment.repositories.JobRepo;
import com.nimesa.assignment.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;

    @Override
    public ResponseEntity<?> GetJobResult(String jobId) {
        jobId = Optional.ofNullable(jobId).map(String::trim).orElseThrow(() -> {
            throw new MissingRequiredParamException("jobId");
        });
        String finalJobId = jobId;
        Job job = jobRepo.findById(jobId).orElseThrow(() -> {
            throw new EntityNotFoundException("job", finalJobId);
        });
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(job.getStatus())
                        .build());
    }

    @Override
    public void saveJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public Job createAndGetJob() {
        Job job = Job.builder()
                .createdOn(Instant.now().toEpochMilli())
                .status(Status.in_process)
                .id(UUID.randomUUID().toString())
                .build();
        return jobRepo.save(job);
    }
}
