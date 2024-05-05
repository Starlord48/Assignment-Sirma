package com.project.managementSystem.services;

import java.util.List;

import com.project.managementSystem.exceptions.ProjectException;
import com.project.managementSystem.models.Project;

public interface ProjectService {
	
	//Create 
	public Project createProject(Project project) throws ProjectException;
	
	//Read
	public Project findProjectById(Long id) throws ProjectException;
	public List<Project> getAllProjects();
	
	//Update
	public Project updateProject(Project project)throws ProjectException;
	
	//Delete
    public void deleteProjectById(Long id) throws ProjectException;

}