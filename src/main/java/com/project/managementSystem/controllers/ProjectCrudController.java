package com.project.managementSystem.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.managementSystem.exceptions.ProjectException;
import com.project.managementSystem.models.Project;
import com.project.managementSystem.services.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProjectCrudController {
	
	@Autowired
	private ProjectService projectService;
	
	@Operation(summary = "Create a new project", description = "Returns a created user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created project"),
        @ApiResponse(responseCode = "400", description = "Validation error")
    })
	@PostMapping("/project")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) throws ProjectException {
        Project createdProject= projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }
    @Operation(summary = "Get project by ID", description = "Returns a single user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
	@GetMapping("/project/{id}")
    public ResponseEntity<?> readProject(@PathVariable Long id) throws ProjectException {
        Project project = projectService.findProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id:"+id+" not found");
//            throw new ProjectException("Project with id: "+ id + "does not exists");
        }
    }

    @Operation(summary = "Update an existing project",  description = "Update details of the project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "returns updated project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PutMapping("/project/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody Project project) throws ProjectException {
        project.setId(id);
        Project findProject = projectService.findProjectById(id);
        
        if (findProject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id:"+id+" not found");
//            throw new ProjectException("Project with id: "+ id + " does not exists");
        } 
        
        Project updatedProject = projectService.updateProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    @Operation(summary = "Delete an existing project", description = "Delete a project by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @DeleteMapping("/project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) throws ProjectException {
        Project findProject = projectService.findProjectById(id);
        
        if (findProject == null) {
            throw new ProjectException("Project with id: "+ id + " does not exists");
        }
        
        
        projectService.deleteProjectById(id);
        return ResponseEntity.ok().body("Successfully deleted project");
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	Map<String, String> errors = new HashMap<>();
    	
    	ex.getBindingResult().getAllErrors().forEach((error)->{
    		String fieldName = ((FieldError) error).getField();
    		String errorMessage = error.getDefaultMessage();
    		errors.put(fieldName, errorMessage);
    	});
    	
		return errors;
    	
    }
}
