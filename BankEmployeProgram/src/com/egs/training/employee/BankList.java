package com.egs.training.employee;

import java.io.IOException;
/**
 *  BankList. The purpose of this class is adding banks and changing bank funds.
 */
import java.util.List;

public class BankList {
	 private List<Bank> banks;
	 private BankFileManager  bankFileManager;
	 
	 /**
		 * Constructor for {@code BankList}.
		 * Loads {@code Bank} objects
		 * from the file and keeps them in the list.
		 * 
		 * @throws IOException I/O error occurred
		 */
	 public BankList(String filePath) throws IOException {
		 bankFileManager = new BankFileManager(filePath);
	     banks = bankFileManager.readBanks();
	 }
	 
	 /**
		 * Adds {@code Bank} object
		 * 
		 * @param bank
		 *           bank to add
		 */
	 
	public void addBank(Bank bank){
		banks.add(bank);
	}
		
	/**
	 * Saves banks to file.
	 * 
	 * @throws IOException
	 *             I/O error occurred
	 */
	public void saveBanks() throws IOException {
        if (banks == null || banks.isEmpty()) {
            return;
        }
        bankFileManager.writeBanks(banks);
    }
	
	/**
	 * Get all banks
	 * 
	 * @return banks
	 */
	public  List<Bank> getBanks(){
		return banks;
	}
	
	/**
	 * change funds
	 */
	public void changeFunds(int bankId, int amount){
		for(Bank bank: banks){
			if(bank.getBankId() == bankId){
				bank.setFunds(bank.getFunds() + amount);
			}
		}
	}
}
