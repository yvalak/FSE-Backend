package com.training.cognizant.fse.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.training.cognizant.fse.entities.Project;

@NoRepositoryBean
public interface ProjectDaoCustom {
	List<Project> getProjectsWithTaskSummary(Sort sort);
}
