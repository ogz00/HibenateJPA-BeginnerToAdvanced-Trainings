package org.oguz.orm.dao.impl;

import java.util.List;

import org.oguz.orm.dao.UserDao;
import org.oguz.orm.dao.common.AbstractDao;
import org.oguz.orm.entities.User;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	public UserDaoImpl() {
		super();
		setPersistenceClass(User.class);
	}

	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
