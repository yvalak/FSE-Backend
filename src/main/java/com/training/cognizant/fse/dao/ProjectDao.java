package com.training.cognizant.fse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.cognizant.fse.entities.Project;

@Repository
public interface ProjectDao extends CrudRepository<Project, Long>, ProjectDaoCustom {
	
}
