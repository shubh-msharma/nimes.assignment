package com.nimesa.assignment.models;

import com.nimesa.assignment.enums.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
