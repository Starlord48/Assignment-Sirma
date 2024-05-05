package com.project.managementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.managementSystem.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
