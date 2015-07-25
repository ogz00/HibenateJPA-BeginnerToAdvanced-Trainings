package org.oguz.orm.data.entities.ids;

import java.io.Serializable;

public class CurrencyId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861785801824058563L;

	private String name;

	private String countryName;

	public CurrencyId() {
	}

	public CurrencyId(String name, String countryName) {
		this.name = name;
		this.countryName = countryName;
	}

	public String getName() {
		return name;
	}

	public String getCountryName() {
		return countryName;
	}

}
