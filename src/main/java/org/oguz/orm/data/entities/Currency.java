package org.oguz.orm.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.oguz.orm.data.entities.ids.CurrencyId;

@Entity
@IdClass(CurrencyId.class)
@Table(name = "currency")
public class Currency {
	@Id
	@Column(name = "NAME")
	private String name;

	@Id
	@Column(name = "COUNTRY_NAME")
	private String countryName;

	@Column(name = "SYMBOL")
	private String Symbol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

}
