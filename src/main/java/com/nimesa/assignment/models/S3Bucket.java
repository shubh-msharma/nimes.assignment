package com.nimesa.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "buckets")
public class S3Bucket {
    @Id
    private String id;
    @Column
    private String name;
    @Column(name = "object_count")
    private int objectCount;
}
