package org.oguz.orm.dao;

import java.util.List;

import org.oguz.orm.entities.User;

public interface UserDao extends Dao<User, Long> {

	public List<User> findByFirstName(String firstName);

}
