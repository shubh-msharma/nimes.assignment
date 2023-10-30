package com.nimesa.assignment.models.entities;

import com.nimesa.assignment.enums.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    private String id;
    @Column(name = "created_on")
    private Instant createdOn;
    @Column
    private Status status;
    @Column
    private String reason;
}
