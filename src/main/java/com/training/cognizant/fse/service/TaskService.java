package com.training.cognizant.fse.service;

import java.util.List;

import com.training.cognizant.fse.entities.Task;


public interface TaskService extends IOperations<Task> {
	List<Task> findByProjectId(long projectId);

}
