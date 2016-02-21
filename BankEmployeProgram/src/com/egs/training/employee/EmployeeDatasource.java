package com.egs.training.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Employee datasource. The purpose of this class is adding, filtering and
 * sorting employees.
 *
 */
public class EmployeeDatasource {
	private List<Employee> employees;
	private EmployeeFileManager employeeFileManager;
	private int maxId = 0;
	/**
	 * Constructor for {@code EmployeeDatasource} Loads {@code Employee} objects
	 * from the file and keeps them in the list.
	 * 
	 * @throws IOException  I/O error occurred
	 */
	public EmployeeDatasource(String filePath, String filePath2)
			throws IOException {
		employeeFileManager = new EmployeeFileManager(filePath, filePath2);
		employees = employeeFileManager.readEmployees();
	}

	/**
	 * Adds {@code Employee} object
	 * 
	 * @param employee
	 *            employee to add
	 */
	public void add(Employee employee) {
		employees.add(employee);
	}
	/**
	 * employees count
	 * 
	 */
	public int maxId() {
		return ++maxId;
	}

	/**
	 * Saves employees to file.
	 * 
	 * @throws IOException
	 *             I/O error occurred
	 */
	public void save() throws IOException {
		if (employees == null || employees.isEmpty()) {
			return;
		}
		employeeFileManager.writeEmployees(employees);
	}

	/**
	 * Get all employees
	 * 
	 * @return employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Filter employees by profession. returns all {@code employees} that start
	 * with given {@code profession} (case insensitive).
	 * 
	 * @param profession
	 *            the profession
	 * @return {@code employees} whose profession starts with given
	 *         {@code profession} (case insensitive)..
	 */
	public List<Employee> filterEmloyeesByProfession(String profession) {
		List<Employee> newEmployees = new ArrayList<>();
		for (Employee employee : employees) {
			if (employee.getProfession().toLowerCase()
					.startsWith(profession.toLowerCase())) {
				newEmployees.add(employee);
			}
		}
		return newEmployees;
	}

	public void paySalary(int id) {
		for (Employee employee : employees) {
			if (employee.getEmployeeId() == id) {
				employee.getBank().setAmount(
						employee.getSalary() + employee.getBank().getAmount());
				break;
			}
		}
	}

	public Employee getEmployee(int id) {
		Employee value = null;
		for (Employee employee : employees) {
			if (employee.getEmployeeId() == id) {
				value = employee;
				break;
			}
		}
		if(value!=null){
			return value;
		}else{
			return null;
		}

	}

	public void spendMoney(int id, int amount) {
		for (Employee employee : employees) {
			if (employee.getEmployeeId() == id) {
				employee.getBank().setAmount(
						employee.getBank().getAmount() - amount);
			}
			break;
		}
	}

	/**
	 * Sorts employees by {@code surname} field
	 * 
	 * @return {@code employees} sorted by {@code surname} field
	 */
	public List<Employee> sortBySurname() {
		Collections.sort(employees, new EmployeeSurnameComparator());
		return employees;
	}

	/**
	 * Sorts employees by {@code salary} field
	 * 
	 * @return {@code employees} sorted by {@code salary} field
	 */
	public List<Employee> sortBySalary() {
		Collections.sort(employees, new EmployeeSalaryComparator());
		return employees;
	}
}
