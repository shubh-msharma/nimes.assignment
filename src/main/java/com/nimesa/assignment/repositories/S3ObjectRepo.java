package com.nimesa.assignment.repositories;

import com.nimesa.assignment.models.entities.S3Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface S3ObjectRepo extends JpaRepository<S3Object,String> {
    @Query("SELECT s3Object FROM S3Object s3Object WHERE s3Object.bucketName = :bucketName AND s3Object.objectKey LIKE %:pattern%")
    List<S3Object> getS3BucketObjectLike(@Param("bucketName") String bucketName, @Param("pattern") String pattern);

}
