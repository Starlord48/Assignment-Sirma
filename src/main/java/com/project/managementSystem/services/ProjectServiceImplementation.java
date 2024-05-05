package com.project.managementSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.managementSystem.exceptions.ProjectException;
import com.project.managementSystem.models.Project;
import com.project.managementSystem.repositories.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectServiceImplementation implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	@Transactional
	public Project createProject(Project project) throws ProjectException {
		return this.projectRepository.save(project);
	}

	@Override
	public Project findProjectById(Long id) throws ProjectException {
		Optional<Project> optionalProject = projectRepository.findById(id);
		Project project = null;
		if(optionalProject.isPresent()) {
			project = optionalProject.get();
		}
		return project;
	}

	@Override
	public List<Project> getAllProjects() {
		
		return projectRepository.findAll();
	}

	@Override
	@Transactional
	public Project updateProject(Project req) throws ProjectException {
		Project project = findProjectById(req.getId());
		
		if(req.getName() != null) {
			project.setName(req.getName());
		}
		if(req.getDescription() != null) {
			project.setDescription(req.getDescription());
		}
		if(req.getStartDate() != null) {
			project.setStartDate(req.getStartDate());
		}
		if(req.getEndDate() != null) {
			project.setEndDate(req.getEndDate());
		}
		if(req.getMajorTask() != null) {
			project.setMajorTask(req.getMajorTask());
		}
		if(req.getTeamsCount() != 0) {
			project.setTeamsCount(req.getTeamsCount());
		}
		
		return projectRepository.save(project);
		
	}

	@Override
	@Transactional
	public void deleteProjectById(Long id) throws ProjectException {
		// TODO Auto-generated method stub
		this.projectRepository.deleteById(id);
	}

}
