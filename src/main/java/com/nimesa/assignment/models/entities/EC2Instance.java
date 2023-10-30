package com.nimesa.assignment.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

import java.time.Instant;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "instances")
public class EC2Instance {
    @Id
    private String id;
    @Column(name = "created_on")
    private Instant createdOn;

}
