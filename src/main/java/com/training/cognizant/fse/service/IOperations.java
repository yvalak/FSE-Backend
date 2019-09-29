package com.training.cognizant.fse.service;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {
	
	T findById(final long id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
