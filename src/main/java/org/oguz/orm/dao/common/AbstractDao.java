package org.oguz.orm.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.oguz.orm.dao.Dao;
import org.oguz.orm.data.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

public class AbstractDao<T, ID extends Serializable> implements Dao<T, ID> {

	private Class<T> persistenceClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// public void setSession(Session session) {
	// this.session = session;}
	// protected Session getSession() {
	// if (this.session == null) {
	// this.session = HibernateUtil.getSessionFactory()
	// .getCurrentSession();}
	// return this.session;}

	protected final Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected final void setPersistenceClass(final Class<T> persistenceClass) {
		this.persistenceClass = Preconditions.checkNotNull(persistenceClass);
	}

	public Class<T> getPersistentClass() {
		return persistenceClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T) getCurrentSession().load(this.persistenceClass, id);
	}

	public List<T> findAll() {
		return this.findByCriteria();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = this.getCurrentSession().createCriteria(
				this.getPersistentClass());

		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (List<T>) crit.list();
	}

	public T save(T entity) {
		this.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(T entity) {
		this.getCurrentSession().delete(entity);

	}

	public void flush() {
		this.getCurrentSession().flush();

	}

	public void clear() {
		this.getCurrentSession().clear();

	}

}
