package com.project.managementSystem.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Valid
	@NotNull(message = "The Project name is mandatory")
	@NotBlank(message = "The Project name is mandatory")
	@Size(min=2, max=100, message = "The Project name must be between 2 and 100 characters")
	private String name;
	
	@Valid
	@NotNull(message = "Try to give a description")
	@NotBlank(message = "Try to give a description")
	@Size(min=5, max=1000, message = "The Project description must be between 5 and 1000 characters")
	private String description;
	
	@Valid
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
	private Date startDate;
	
	@Valid
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
	private Date endDate;
	
	private int teamsCount;
	private String majorTask;
	
	//constructors
	public Project() {
		super();
	}
	
	public Project(Long id, String name, String description, Date startDate, Date endDate, int teamsCount,
			String majorTask) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.teamsCount = teamsCount;
		this.majorTask = majorTask;
	}
	
	//getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTeamsCount() {
		return teamsCount;
	}
	public void setTeamsCount(int teamsCount) {
		this.teamsCount = teamsCount;
	}
	public String getMajorTask() {
		return majorTask;
	}
	public void setMajorTask(String majorTask) {
		this.majorTask = majorTask;
	}
	

    private Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.teamsCount = builder.teamsCount;
        this.majorTask = builder.majorTask;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Date startDate;
        private Date endDate;
        private int teamsCount;
        private String majorTask;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder teamsCount(int teamsCount) {
            this.teamsCount = teamsCount;
            return this;
        }

        public Builder majorTask(String majorTask) {
            this.majorTask = majorTask;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

}
