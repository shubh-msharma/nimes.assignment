package com.nimesa.assignment.repositories;

import com.nimesa.assignment.models.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,String> {
}
