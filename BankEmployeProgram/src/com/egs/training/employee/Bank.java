package com.egs.training.employee;

public class Bank {
	/**
	 * Bank name
	 */
	private String name;
	/**
	 * Bank funds
	 */
	private int funds;
	/**
	 * Bank bankId
	 */
	private int bankId;

	void setName(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	void setFunds(int funds) {
		this.funds = funds;
	}

	int getFunds() {
		return funds;
	}

	void setBankId(int bankId) {
		this.bankId = bankId;
	}

	int getBankId() {
		return bankId;
	}
}
