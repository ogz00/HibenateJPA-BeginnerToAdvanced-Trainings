package org.oguz.orm.services;

import java.io.Serializable;

import org.oguz.orm.dao.Dao;

public interface Service<T, ID extends Serializable>extends Dao<T, ID> {

}
