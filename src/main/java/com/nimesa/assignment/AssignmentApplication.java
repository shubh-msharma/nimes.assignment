package com.nimesa.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
1. DiscoverServices(List<String> services)

Input: Services are EC2 and S3
Output: JobId

This method should asynchronously discover EC2 instances in the Mumbai Region in one thread and S3 buckets in another thread and persist the result in DB

2. GetJobResult(Jobid)

Input: JobId
Output: Success or In Progress or Failed

This method should return the Job Status for the given ID

3. GetDiscoveryResult(String Service)

Input: Service Name
Output: For S3 - List of S3 Buckets
For EC2 - List of Instance IDs

4. GetS3BucketObjects(String BucketName)

Input: S3 bucket name
Output: JobId

Discover all the File Names in the S3 bucket and persist in the DB. Return the JobID as it may take time for the job the complete

5. GetS3BucketObjectCount(String BucketName)

Input: S3 bucket name
Output: Count

Get the result from the DB

6. GetS3BucketObjectlike(String BucketName, String Pattern)

Input: S3 bucket name
Output: List of file names matching the pattern

Get the result from the DB
 */
@SpringBootApplication
@EnableSwagger2
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
