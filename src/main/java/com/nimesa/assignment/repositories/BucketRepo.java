package com.nimesa.assignment.repositories;

import com.nimesa.assignment.models.S3Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepo extends JpaRepository<S3Bucket,String> {
}
