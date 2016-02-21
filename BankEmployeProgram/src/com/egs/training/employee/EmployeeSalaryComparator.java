package com.egs.training.employee;

import java.util.Comparator;

/**
 * Comparator that compares two {@code Employee} objects by {@code salary} field
 *
 * @author movsesd
 */
public class EmployeeSalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getSalary() > o2.getSalary()) {
            return 1;
        }
        if (o1.getSalary() < o2.getSalary()) {
            return -1;
        }
        return 0;
    }
}
