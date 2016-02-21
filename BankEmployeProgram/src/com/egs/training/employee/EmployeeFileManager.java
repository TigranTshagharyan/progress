package com.egs.training.employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Employee file manager.
 * Purpose of this class is to read employees from file / save employees to file
 *
 */
public class EmployeeFileManager {
    private String filePath;
    private String filePath2;

    public EmployeeFileManager(String filePath,String filePath2) {
        this.filePath = filePath;
        this.filePath2 = filePath2;
    }

    /**
     * Read banks from file
     * @return banks from file
     * @throws IOException I/O error occurred
     */
    public List<Employee> readEmployees() throws IOException {
        List<Employee> employees;
        DataInputStream dst = null;
        DataInputStream bdis = null;
        try {
            employees = new ArrayList<>();
            File file = new File(filePath);
            File bfile = new File(filePath2);
            if ((!file.exists() && !file.isDirectory())||(!bfile.exists() && !bfile.isDirectory())) {
                return employees;
            }
            
            FileInputStream fis = new FileInputStream(file);
            FileInputStream bfis = new FileInputStream(bfile);
            dst = new DataInputStream(fis);
            bdis = new DataInputStream(bfis);
            int count;
            try {
                count = dst.readInt();
            } catch (EOFException e) {
                return employees;
            }

            for (int i = 0; i < count; i++) {
                Employee employee = new Employee();
                employee.setName(dst.readUTF());
                employee.setSurname(dst.readUTF());
                employee.setProfession(dst.readUTF());
                employee.setSalary(dst.readInt());
                employee.setEmployeeId(dst.readInt());
                employee.getBank().setAmount(bdis.readInt());
                employee.getBank().setAccountNumber(bdis.readLong());
                employee.getBank().setBankId(bdis.readInt());
                employees.add(employee);
            }
        } finally {
            if (dst != null) {
                dst.close();
            }
            if (bdis != null) {
                bdis.close();
            }
        }

        return employees;
    }

    /**
     * Write banks to file.
     * @param banks employees
     * @throws IOException I/O error occurred
     */
    public void writeEmployees(List<Employee> employees) throws IOException {
        DataOutputStream dos = null;
        DataOutputStream gdos = null;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            FileOutputStream gfos = new FileOutputStream(filePath2);
            dos = new DataOutputStream(fos);
            gdos =  new DataOutputStream(gfos);
            dos.writeInt(employees.size());

            for (Employee employee : employees) {
                dos.writeUTF(employee.getName());
                dos.writeUTF(employee.getSurname());
                dos.writeUTF(employee.getProfession());
                dos.writeInt(employee.getSalary());
                dos.writeInt(employee.getEmployeeId());
                gdos.writeInt(employee.getBank().getAmount());
                gdos.writeLong(employee.getBank().getAccountNumber());
                gdos.writeInt(employee.getBank().getBankId());
            }
        } finally {
            if (dos != null) {
                dos.close();
            }
            if (gdos != null){
            	gdos.close();
            }
        }
    }
}
