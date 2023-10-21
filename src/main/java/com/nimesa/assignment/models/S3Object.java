package com.nimesa.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "objects")
public class S3Object {
    @Id
    private String id;
    @Column(name = "bucket_id")
    private String bucketId;
    @Column(name = "bucket_name")
    private String bucketName;
    @Column(name = "object_key")
    private String objectKey;
    @Column(name = "e_tag")
    private String eTag;
    @Column
    private long size;
    @Column(name = "storage_class")
    private String storageClass;
}
