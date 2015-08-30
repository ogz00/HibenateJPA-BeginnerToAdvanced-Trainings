package org.oguz.orm.services.impl;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.entities.Bank;
import org.oguz.orm.services.BankService;
import org.oguz.orm.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public class BankServiceImpl extends AbstractService<Bank, Long> implements
		BankService {

	@Autowired
	private Dao<Bank, Long> dao;

	public BankServiceImpl() {
		super();
	}

	@Override
	protected Dao<Bank, Long> getDao() {
		return dao;
	}

}
