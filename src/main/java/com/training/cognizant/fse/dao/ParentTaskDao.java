package com.training.cognizant.fse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.cognizant.fse.entities.ParentTask;


@Repository
public interface ParentTaskDao extends CrudRepository<ParentTask, Long> {

}
