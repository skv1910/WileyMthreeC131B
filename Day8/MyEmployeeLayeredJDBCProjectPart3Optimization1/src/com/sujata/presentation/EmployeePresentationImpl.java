package com.sujata.presentation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

import com.sujata.bean.Employee;
import com.sujata.bean.EmployeePaySlip;
import com.sujata.service.EmployeeService;
import com.sujata.service.EmployeeServiceImpl;

public class EmployeePresentationImpl implements EmployeePresentation {

	private EmployeeService employeeService=new EmployeeServiceImpl();
	@Override
	public void showMenu() {
		System.out.println("1. List All Employees");
		System.out.println("2. Insert Employee ");
		System.out.println("3. Generate Payslip");
		System.out.println("4. Exit");

	}

	@Override
	public void performMenu(int choice) {
		Scanner scanner=new Scanner(System.in);
		switch(choice) {
		case 1:
			Collection<Employee> employees=null;
			try {
				employees = employeeService.getAllEmployees();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			for(Employee emp:employees)
				System.out.println(emp);
			break;
		case 2:
			System.out.println("Enter Employee ID : ");
			int id=scanner.nextInt();
			System.out.println("Enter Employee Name : ");
			String na=scanner.next();
			System.out.println("Enter Designation : ");
			String des=scanner.next();
			System.out.println("Enter Department : ");
			String deptt=scanner.next();
			System.out.println("Enter Salary : ");
			int sal=scanner.nextInt();
			Employee employee=new Employee(id, na, des, deptt, sal);
			try {
				if(employeeService.insertEmployee(employee))
					System.out.println("Employee Added Successfully!");
				else
					System.out.println("Employee Addition Failed!");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter Employee ID : ");
			int eId=scanner.nextInt();
			EmployeePaySlip empPaySlip=null;
			try {
				empPaySlip = employeeService.getPaySlip(eId);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			if(empPaySlip!=null)
				System.out.println(empPaySlip);
			else
				System.out.println("Employee with id "+eId+" does not exist");
			break;
		case 4:
			System.out.println("Thanks for using employee management system!");
			System.exit(0);
		}

	}

}
