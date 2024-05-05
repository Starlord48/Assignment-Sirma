package com.project.managementSystem.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.project.managementSystem.models.Project;
import com.project.managementSystem.repositories.ProjectRepository;
import com.project.managementSystem.services.ProjectServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
	
	@Mock
	private ProjectRepository projectRepository;
	
	@InjectMocks
	private ProjectServiceImplementation projectService;
	
	@Test
	public void ProjectService_CreateProject_ReturnProject() {
		
		//Arrange
		Project project = new Project.Builder()
				.name("first Project")
				.description("This is the first project")
				.build();
		
		//mock repository behaviour
	    when(projectRepository.save(any(Project.class))).thenReturn(project);
	    
	    Project createdProject = projectService.createProject(project);

	    // Assert the result
	    assertNotNull(createdProject);
	    assertEquals("first Project", createdProject.getName());
	    assertEquals("This is the first project", createdProject.getDescription());
	    assertNull(createdProject.getStartDate());
	    assertNull(createdProject.getEndDate());
	    assertEquals(0,createdProject.getTeamsCount());		
	    assertNull(createdProject.getMajorTask());		
	}
	
	@Test
	public void ProjectService_UpdateProject_ReturnProject() {
		Long projectId = 1L;
        Project existingProject = new Project.Builder()
        		.id(projectId)
        		.name("Existing Project")
        		.description("This is a existing project")
        		.build();
        // Set other existingProject properties

        Project updatedProject = new Project();
        updatedProject.setId(projectId); // Setting the ID of the updated project
        updatedProject.setName("Updated Project");
        // Set other updatedProject properties

        // Mock repository behavior
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(any(Project.class))).thenReturn(updatedProject);

        // Call the service method
        Project result = projectService.updateProject(updatedProject);

        // Verify the result
        assertNotNull(result);
        assertEquals(projectId, result.getId());
        assertEquals("Updated Project", result.getName());
        assertNull(result.getStartDate());
	    
	}
	
	@Test
	public void ProjectService_findProjectById_ReturnProject() {
		
		Long projectId = 1L;
		Project project = new Project.Builder()
				.name("test Project")
				.description("This is a test project")
				.teamsCount(3)
				.build();
		
	    when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
	    
	    Project createdProject = projectService.findProjectById(projectId);

	    assertNotNull(createdProject);
	    assertEquals("test Project", createdProject.getName());
	    assertEquals("This is a test project", createdProject.getDescription());
	    assertNull(createdProject.getStartDate());
	    assertNull(createdProject.getEndDate());
	    assertEquals(3,createdProject.getTeamsCount());		
	    assertNull(createdProject.getMajorTask());		
	}
	
	@Test
    public void ProjectService_deleteProject_ReturnVoid() {
		Long projectId = 1L;

        projectService.deleteProjectById(projectId);

        // Verify that the repository method was called with the correct argument
        verify(projectRepository, times(1)).deleteById(projectId);
    }

}
