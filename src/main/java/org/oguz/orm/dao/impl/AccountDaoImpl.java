package org.oguz.orm.dao.impl;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.dao.common.AbstractDao;
import org.oguz.orm.entities.Account;

public class AccountDaoImpl extends AbstractDao<Account, Long> implements
		Dao<Account, Long> {

	public AccountDaoImpl() {
		super();
		setPersistenceClass(Account.class);
	}
}
