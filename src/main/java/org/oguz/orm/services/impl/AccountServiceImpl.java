package org.oguz.orm.services.impl;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.entities.Account;
import org.oguz.orm.services.AccountService;
import org.oguz.orm.services.Service;
import org.oguz.orm.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl extends AbstractService<Account, Long>
		implements Service<Account,Long> {

	@Autowired
	private Dao<Account, Long> dao;

	public AccountServiceImpl() {
		super();
	}

	@Override
	protected Dao<Account, Long> getDao() {
		return dao;
	}

}
