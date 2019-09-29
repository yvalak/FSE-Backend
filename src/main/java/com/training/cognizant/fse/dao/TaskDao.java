package com.training.cognizant.fse.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.cognizant.fse.entities.Task;


@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
	@Query(value = "SELECT t.* FROM TASK t WHERE t.Project_ID=?1", nativeQuery = true)
	List<Task> findByProject(long projectId);

}
