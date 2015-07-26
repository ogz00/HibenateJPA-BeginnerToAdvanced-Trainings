package org.oguz.orm.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PORTFOLIO_ID")
	private long portfolioId;

	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy="portfolio")
	private List<Investment> investment = new ArrayList<Investment>();

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Investment> getInvestment() {
		return investment;
	}

	public void setInvestment(List<Investment> investment) {
		this.investment = investment;
	}

}
