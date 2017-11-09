package com.bloomberg.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "accumulative_deals")
public class AccumulativeDeal {

	private Integer id;
	private String oderingCurrency;
	private BigInteger count;

	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ordering_currency")
	public String getOderingCurrency() {
		return oderingCurrency;
	}

	public void setOderingCurrency(String oderingCurrency) {
		this.oderingCurrency = oderingCurrency;
	}

	@Column(name = "count")
	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

}
