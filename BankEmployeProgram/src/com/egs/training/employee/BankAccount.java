package com.egs.training.employee;

public class BankAccount {
	/**
     * Employee bankId
     */
	private int bankId;
	/**
     * Employee amount
     */
	private int amount;
	/**
     * Employee accountNumber
     */
	private long accountNumber;
	
	public void setBankId(int bankId) {
	        this.bankId = bankId;
	  }
	public int getBankId() {
	        return bankId;
	    }
	public void setAmount(int amount) {
	        this.amount = amount;
	  }
	 public int getAmount() {
	        return amount;
	    }
	 public void setAccountNumber(long accountNumber) {
	        this.accountNumber = accountNumber;
	  }
	 public long getAccountNumber() {
		 return accountNumber;
	    }
}
