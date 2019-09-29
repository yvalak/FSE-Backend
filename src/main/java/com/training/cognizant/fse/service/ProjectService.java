package com.training.cognizant.fse.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.training.cognizant.fse.entities.Project;


public interface ProjectService extends IOperations<Project> {
	
	List<Project> getProjectsWithTaskSummary(Sort sort);

}
