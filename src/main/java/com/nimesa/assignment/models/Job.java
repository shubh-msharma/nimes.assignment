package com.nimesa.assignment.models;

import com.nimesa.assignment.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    @Column
    private long createdOn;
    @Column
    private Status status;
    @Column
    private String reason;
}
