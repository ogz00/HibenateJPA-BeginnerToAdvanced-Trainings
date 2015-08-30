package org.oguz.orm.dao.impl;

import org.oguz.orm.dao.Dao;
import org.oguz.orm.dao.common.AbstractDao;
import org.oguz.orm.entities.Bank;

public class BankDaoImpl extends AbstractDao<Bank, Long> implements Dao<Bank, Long> {
	
	public BankDaoImpl() {
		super();
		setPersistenceClass(Bank.class);
	}

}
