package org.oguz.orm.services.common;

import java.io.Serializable;
import java.util.List;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.services.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService <T, ID extends Serializable> implements Service<T, ID>{

	@Override
	public T findById(ID id) {
		return getDao().findById(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
		
	}

	@Override
	public void flush() {
		getDao().flush();
		
	}

	@Override
	public void clear() {
		getDao().clear();
		
	}
	
	protected abstract Dao<T, ID> getDao();

}
