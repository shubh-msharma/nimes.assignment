package com.nimesa.assignment.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.nimesa.assignment.utils.EnvironmentVariables;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.flywaydb.core.api.pattern.ValidatePattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class Beans {
    @Bean
    public AmazonEC2 amazonEC2(){
        try {
            BasicAWSCredentials credentials = new BasicAWSCredentials(EnvironmentVariables.AWS_ACCESS_KEY, EnvironmentVariables.AWS_SECRET_KEY);
            return AmazonEC2ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_SOUTH_1)
                    .build();
        }catch (Exception e){
            return AmazonEC2ClientBuilder.defaultClient();
        }
    }

    @Bean
    public AmazonS3 amazonS3(){
        try {
            BasicAWSCredentials credentials = new BasicAWSCredentials(EnvironmentVariables.AWS_ACCESS_KEY, EnvironmentVariables.AWS_SECRET_KEY);
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_SOUTH_1)
                    .build();
        }catch (Exception e){
            return AmazonS3ClientBuilder.defaultClient();
        }
    }

//    @Bean
//    public Flyway flyway() {
//        Flyway flyway = new Flyway(new ClassicConfiguration().setIgnoreMigrationPatterns(ValidatePattern.fromPattern()););
//        return flyway;
//    }
}
