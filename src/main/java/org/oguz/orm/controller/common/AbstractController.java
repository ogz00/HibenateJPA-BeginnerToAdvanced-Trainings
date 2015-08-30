package org.oguz.orm.controller.common;

import java.io.Serializable;
import java.util.List;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.services.common.AbstractService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController <T, ID extends Serializable> implements Dao<T, ID>{
	
	

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	

}
