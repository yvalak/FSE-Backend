package com.training.cognizant.fse.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.training.cognizant.fse.dao.ProjectDao;
import com.training.cognizant.fse.entities.Project;
import com.training.cognizant.fse.service.AbstractService;
import com.training.cognizant.fse.service.ProjectService;

@Service("ProjectService")
public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	public ProjectServiceImpl() {
	}

	@Override
	protected CrudRepository<Project, Long> getDao() {
		return projectDao;
	}

	@Override
	public List<Project> getProjectsWithTaskSummary(Sort sort) {
		return projectDao.getProjectsWithTaskSummary(sort);
	}

}
