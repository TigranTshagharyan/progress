package com.egs.training.employee;
/**
 *
 * */
public class Employee {
    /**
     * Employee name
     */
    private String name;
    /**
     * Employee surname
     */
    private String surname;
    /**
     * Employee profession
     */
    private String profession;
    /**
     * Employee salary
     */
    private int salary;
    /**
     * Employee employeeId
     */
    private int employeeId;
    /**
     * Employee bank account
     */
    private BankAccount bank;

    Employee(){
		bank = new BankAccount();
	}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public int getEmployeeId(){
    	return employeeId;
    }
    public void setEmployeeId(int id){
    	this.employeeId = id;
    }
    
    public BankAccount getBank(){
    	return bank;
    }
   
    
}
