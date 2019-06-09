package net.codejava.hibernate;

import java.util.Set;

public class Employee {
    private int employeeId;
    private String employeeName;
    private Set<String> phoneNumbers;
 
    public int getEmployeeId() {
        return employeeId;
    }
 
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
 
    public String getEmployeeName() {
        return employeeName;
    }
 
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
 
    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }
 
    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
 
}