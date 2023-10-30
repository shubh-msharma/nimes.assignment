package com.nimesa.assignment.services.impl;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.nimesa.assignment.models.entities.EC2Instance;
import com.nimesa.assignment.models.dtos.ApiResponse;
import com.nimesa.assignment.repositories.InstanceRepo;
import com.nimesa.assignment.services.InstanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstanceServiceImpl implements InstanceService {

    private final AmazonEC2 amazonEC2Client;
    private final InstanceRepo instanceRepo;

    @Override
    public boolean discoverAndSaveInstances() {
        try {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = amazonEC2Client.describeInstances(request);
            for (Reservation reservation : response.getReservations()){
                for (Instance instance : reservation.getInstances()) {
                    EC2Instance ec2Instance = EC2Instance.builder()
                            .id(instance.getInstanceId())
                            .createdOn(Instant.now())
                            .build();
                    instanceRepo.save(ec2Instance);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return true;
    }

    @Override
    public ResponseEntity<?> getInstances() {
        List<EC2Instance> instanceList = instanceRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(HttpStatus.OK)
                        .data(instanceList)
                        .build());
    }
}
