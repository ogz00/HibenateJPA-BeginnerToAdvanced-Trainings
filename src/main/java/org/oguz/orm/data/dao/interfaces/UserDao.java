package org.oguz.orm.data.dao.interfaces;

import java.util.List;

import org.oguz.orm.data.entities.User;

public interface UserDao extends Dao<User, Long> {

	public List<User> findByFirstName(String firstName);

}
