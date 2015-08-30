package org.oguz.orm.services.impl;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.dao.UserDao;
import org.oguz.orm.entities.User;
import org.oguz.orm.services.UserService;
import org.oguz.orm.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl extends AbstractService<User, Long> implements
		UserService {

	@Autowired
	private UserDao dao;

	public UserServiceImpl() {
		super();
	}

	@Override
	protected Dao<User, Long> getDao() {
		return dao;
	}

}
