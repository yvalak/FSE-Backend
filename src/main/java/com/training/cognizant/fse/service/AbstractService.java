package com.training.cognizant.fse.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;



@Component
@Qualifier("accountService")
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	public AbstractService() {
		// TODO Auto-generated constructor stub
	}

    public T findById(final long id) {
        return getDao().findById(id).orElse(null);
    }


    @Override
    public List<T> findAll() {
        return (ArrayList<T> )getDao().findAll();
    }

    @Override
    public T create(final T entity) {
        return getDao().save(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().save(entity);
    }
    
    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }
    
    @Override
    public void deleteById(long entityId) {
        T entity = findById(entityId);
        delete(entity);
    }

    protected abstract CrudRepository<T, Long> getDao();
}
