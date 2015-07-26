package org.oguz.orm.data.dao;

import java.util.List;

import org.oguz.orm.data.dao.interfaces.UserDao;
import org.oguz.orm.data.entities.User;

public class UserHibernateDao extends AbstractDao<User, Long> implements UserDao {

	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
