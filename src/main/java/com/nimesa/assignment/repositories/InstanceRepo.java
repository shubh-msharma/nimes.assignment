package com.nimesa.assignment.repositories;

import com.nimesa.assignment.models.EC2Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepo extends JpaRepository<EC2Instance,String> {
}
