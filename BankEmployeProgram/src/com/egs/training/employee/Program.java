package com.egs.training.employee;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Purpose of this class is to read commands from input and execute them.
 *
 * @author tigran
 */
public class Program {
	private final static String EMP_FILE_PATH = "C:\\Users\\GRIQ\\Desktop\\employee.dat";
	private final static String BNK_ACNT_FILE_PATH = "C:\\Users\\GRIQ\\Desktop\\bankAccount.dat";
	private final static String BANKS_FILE_PATH = "C:\\Users\\GRIQ\\Desktop\\bank.dat";

	private EmployeeDatasource employeeDatasource;
	private BankList bankList;
	/**
	 * Constructor for {@code Program} class.
	 *
	 * Initializes {@code employeeDatasource}.
	 *
	 * @throws IOException
	 *             I/O error occurred
	 */
	public Program() throws IOException {
		employeeDatasource = new EmployeeDatasource(EMP_FILE_PATH, BNK_ACNT_FILE_PATH);
		bankList = new BankList(BANKS_FILE_PATH);
	}

	/**
	 * Starts the program and waits commands from input.
	 * 
	 * @throws IOException
	 *             I/O error occurred
	 */
	private void start() throws IOException {
		Scanner scanner = new Scanner(System.in);
			String command;
			System.out.print("Please enter a command: ");
			while (!(command = scanner.nextLine()).equals("exit")) {
				switch (command) {
				case "paySalary":
					paySalary();
					System.out.println("OK!");
					break;
				case "spendMoney":
					spendMoney();
					break;
				case "add":
					System.out.println("Please enter employee details");
					add();
					break;
				case "addBank":
					System.out.println("Please enter bank details");
					addBank();
					break;
				case "print":
					System.out.println("Printing all employees ...");
					print();
					break;
				case "printBanks":
					System.out.println("Printing all Banks ...");
					printBanks();
					break;
				case "filter":
					System.out.print("Please enter profession:");
					String profession = scanner.nextLine();
					filter(profession);
					break;
				case "sort1":
					System.out.println("Printing employees sorted by surname ...");
					sort1();
					break;
				case "sort2":
					System.out.println("Printing employees sorted by salary ...");
					sort2();
					break;
				case "save":
					System.out.print("Saving employees to file ... ");
					employeeDatasource.save();
					System.out.println("OK!");
					break;
				case "saveBanks":
					System.out.print("Saving banks to file ... ");
					bankList.saveBanks();
					System.out.println("OK!");
					break;
				case "help":
					printHelp();
					break;
				default:
					System.out.println("Wrong command");
					printHelp();
					break;
				}

				System.out.print("\nPlease enter a command: ");
			}
			employeeDatasource.save();
			bankList.saveBanks();
	}

	/**
	 * Prints help screen.
	 */
	private void printHelp() {
		System.out.println("Supported commands:");
		System.out.println("add\t\t - add an employee");
		System.out.println("paySalary\t\t - pay salary an employee");
		System.out.println("spendMoney\t\t - spend money an employee");
		System.out.println("print\t\t - print all employees");
		System.out.println("printBanks\t\t - print all Banks");
		System.out.println("filter\t\t - filter employees by profession");
		System.out.println("sort1\t\t - sort employees by surname");
		System.out.println("sort2\t\t - sort employees by salary");
		System.out.println("save\t\t - save employees");
		System.out.println("saveBanks\t\t - save Banks");
		System.out.println("help\t\t - print supported commands\n");
	}

	/**
	 * Adds new employee.
	 *
	 * @see EmployeeDatasource#add(Employee)
	 */
	private void add() {
		 Scanner scanner = new Scanner(System.in);
			Employee employee = new Employee();
			System.out.print("Name: ");
			employee.setName(scanner.nextLine());
			System.out.print("Surname: ");
			employee.setSurname(scanner.nextLine());
			System.out.print("Profession: ");
			employee.setProfession(scanner.nextLine());
			System.out.print("Salary: ");
			employee.setSalary(scanner.nextInt());
			System.out.print("Amount: ");
			employee.getBank().setAmount(scanner.nextInt());
			System.out.print("Account Number: ");
			employee.getBank().setAccountNumber(scanner.nextLong());
			System.out.print("Bank Id: ");
			employee.getBank().setBankId(scanner.nextInt());
			employee.setEmployeeId(employeeDatasource.maxId());
			employeeDatasource.add(employee);
		}
		
	/**
	 * Adds new bank.
	 *
	 * @see EmployeeDatasource#add(Employee)
	 */
	
	private void addBank() {
		Scanner scanner = new Scanner(System.in);
		Bank bank = new Bank();
		System.out.print("Bank name: ");
		bank.setName(scanner.nextLine());
		System.out.print("Bank Id: ");
		bank.setBankId(scanner.nextInt());
		System.out.print("Bank founds: ");
		bank.setFunds(scanner.nextInt());
		bankList.addBank(bank);
	}
	
	/**
	 * pay salary.
	 */
	private synchronized void paySalary() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please insert employee id ");
		int id = scanner.nextInt();
		employeeDatasource.paySalary(id);
		Employee employee = employeeDatasource.getEmployee(id);
		bankList.changeFunds(employee.getBank().getBankId(),
				employee.getSalary());
	}
	
	/**
	 * spend money.
	 */
	private synchronized void spendMoney(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please insert employee id ");
		int id = scanner.nextInt();
		System.out.print("Please insert employee spent amount ");
		int amount = scanner.nextInt();
		employeeDatasource.spendMoney(id, amount);
		Employee employee = employeeDatasource.getEmployee(id);
		bankList.changeFunds(employee.getBank().getBankId(), -amount);
	}

	/**
	 * Prints all employees.
	 */
	private void print() {
		List<Employee> employees = employeeDatasource.getEmployees();
		print(employees);
	}
	
	/**
	 * Prints all banks.
	 */
	private void printBanks() {
		List<Bank> Banks = bankList.getBanks();
		printBanks(Banks);
	}

	/**
	 * Filters all employees by profession.
	 *
	 * @param profession
	 *            the profession
	 *
	 * @see EmployeeDatasource#filterEmloyeesByProfession(String)
	 */
	private void filter(String profession) {
		List<Employee> employees = employeeDatasource
				.filterEmloyeesByProfession(profession);
		print(employees);
	}

	/**
	 * Sorts employees by surname.
	 *
	 * @see EmployeeDatasource#sortBySurname()
	 */
	private void sort1() {
		List<Employee> employees = employeeDatasource.sortBySurname();
		print(employees);
	}

	/**
	 * Sorts employees by salary.
	 *
	 * @see EmployeeDatasource#sortBySalary()
	 */
	private void sort2() {
		List<Employee> employees = employeeDatasource.sortBySalary();
		print(employees);
	}

	/**
	 * Prints {@code employees}
	 *
	 * @param employees
	 *            the employees
	 */
	private void print(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.printf("%s %s - %s , %s, %s, %s, %s, %s", employee
					.getName(), employee.getSurname(),
					employee.getProfession(), employee.getSalary(), employee
							.getEmployeeId(), employee.getBank().getBankId(),
					employee.getBank().getAccountNumber(), employee.getBank()
							.getAmount());
			System.out.println();
		}
	}
	/**
	 * Prints {@code banks}
	 *
	 */
	private void printBanks(List<Bank> banks) {
		for (Bank bank : banks) {
			System.out.printf("%s, %s, %s", bank.getName(), bank.getFunds(),
					bank.getBankId());
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		new Program().start();
	}
}
