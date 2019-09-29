package com.training.cognizant.fse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.cognizant.fse.entities.User;


@Repository
public interface UserDao extends CrudRepository<User, Long>{

}
