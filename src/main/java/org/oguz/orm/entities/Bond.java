package org.oguz.orm.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@Table(name = "bond")
@DiscriminatorValue("BND")
public class Bond extends Investment{


	@Column(name = "VALUE")
	private BigDecimal value;

	@Column(name = "INTEREST_RATE")
	private BigDecimal interestRate;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "MATURITY_DATE", columnDefinition="DATETIME")
	private Date maturityDate;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

}
