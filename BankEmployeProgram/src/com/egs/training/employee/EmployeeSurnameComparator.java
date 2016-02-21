package com.egs.training.employee;

<<<<<<< .mine
import java.util.Comparator;

/**
 * Comparator that compares two {@code Employee} objects by {@code surname} field
 *
 * @author movsesd
 */
public class EmployeeSurnameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }




















=======
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
>>>>>>> .theirs
}
