package com.attcodingtest;

public class Employee {

	private int id ;
	private String employee_name;
	private double employee_salary;
	private int employee_age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public double getEmployee_salary() {
		return employee_salary;
	}
	public void setEmployee_salary(double employee_salary) {
		this.employee_salary = employee_salary;
	}
	public int getEmployee_age() {
		return employee_age;
	}
	public void setEmployee_age(int employee_age) {
		this.employee_age = employee_age;
	}	
	
	public String toString() {
		return "ID : "+id+", Emp Name: "+employee_name+", Emp Sal: "+employee_salary+", Emp Age: "+employee_age;
	}
}
