package com.egs.training.employee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankFileManager {
	 private String filePath;

	    public BankFileManager(String filePath) {
	        this.filePath = filePath;
	    }

	    /**
	     * Read banks from file
	     * @return banks from file
	     * @throws IOException I/O error occurred
	     */
	    public List<Bank> readBanks() throws IOException {
	        List<Bank> Banks;
	        DataInputStream di = null;
	        try {
	            Banks = new ArrayList<>();
	            File file = new File(filePath);
	            if (!file.exists() && !file.isDirectory()) {
	                return Banks;
	            }
	            
	            FileInputStream fis = new FileInputStream(file);
	            di = new DataInputStream(fis);
	            int count;
	            try {
	                count = di.readInt();
	            } catch (EOFException e) {
	                return Banks;
	            }

	            for (int i = 0; i < count; i++) {
	                Bank bank = new Bank();
	                bank.setName(di.readUTF());
	                bank.setFunds(di.readInt());
	                bank.setBankId(di.readInt());          
	                Banks.add(bank);
	            }
	        } finally {
	            if (di != null) {
	                di.close();
	            }
	        }

	        return Banks;
	    }

	    /**
	     * Write banks to file.
	     * @param banks banks
	     * @throws IOException I/O error occurred
	     */
	    public void writeBanks(List<Bank> banks) throws IOException {
	        DataOutputStream dos = null;
	        try {
	            FileOutputStream fos = new FileOutputStream(filePath);
	            dos = new DataOutputStream(fos);
	            dos.writeInt(banks.size());

	            for (Bank bank : banks) {
	                dos.writeUTF(bank.getName());
	                dos.writeInt(bank.getFunds());
	                dos.writeInt(bank.getBankId());
	            }
	        } finally {
	            if (dos != null) {
	                dos.close();
	            }
	           
	        }
	    }
	}