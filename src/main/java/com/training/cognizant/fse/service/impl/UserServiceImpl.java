package com.training.cognizant.fse.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.training.cognizant.fse.dao.UserDao;
import com.training.cognizant.fse.entities.User;
import com.training.cognizant.fse.service.AbstractService;
import com.training.cognizant.fse.service.UserService;

@Service("UserService")
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CrudRepository<User, Long> getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}

}
