package com.nimesa.assignment.services.impl;

import com.nimesa.assignment.enums.Status;
import com.nimesa.assignment.exceptions.ActionNotAllowedException;
import com.nimesa.assignment.exceptions.MissingRequiredParamException;
import com.nimesa.assignment.models.entities.Job;
import com.nimesa.assignment.models.dtos.ApiResponse;
import com.nimesa.assignment.services.AwsService;
import com.nimesa.assignment.services.BucketService;
import com.nimesa.assignment.services.InstanceService;
import com.nimesa.assignment.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsServiceImpl implements AwsService {

    private final InstanceService instanceService;
    private final BucketService bucketService;
    private final JobService jobService;

    @Override
    public ResponseEntity<?> discoverServices(List<String> services) {
        if(null == services || services.isEmpty()) throw new MissingRequiredParamException("services");
        Job job = jobService.createAndGetJob(Status.in_process);
        new Thread(() -> {
            ExecutorService service = Executors.newFixedThreadPool(2);
            List<Callable<Boolean>> callables = new LinkedList<>();
            services.forEach(s -> {
                if(s.equalsIgnoreCase("s3")) {
                    callables.add(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return bucketService.discoverAndSaveBuckets();
                        }
                    });
                }else if(s.equalsIgnoreCase("ec2")) {
                    callables.add(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return instanceService.discoverAndSaveInstances();
                        }
                    });
                }
            });
            Status jobStatus = Status.success;
            try {
                List<Future<Boolean>> futureTasks = service.invokeAll(callables);
                boolean tempStatus = true;
                for (Future<Boolean> futureTask : futureTasks) {
                    tempStatus = tempStatus && futureTask.get();
                    if(false == tempStatus) jobStatus = Status.failed;
                }
            }catch (Exception e){
                jobStatus = Status.failed;
            }
            job.setStatus(jobStatus);
            jobService.saveJob(job);
        }).start();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .message("submitted request to discover requested services")
                        .data(job.getId())
                        .build());
    }

    @Override
    public ResponseEntity<?> getDiscoveryResult(String service) {
        if("s3".equalsIgnoreCase(service)){
            return bucketService.getBuckets();
        }else if("ec2".equalsIgnoreCase(service)){
            return instanceService.getInstances();
        }
        throw new ActionNotAllowedException("getDiscoveryResult","invalid service name");
    }
}
