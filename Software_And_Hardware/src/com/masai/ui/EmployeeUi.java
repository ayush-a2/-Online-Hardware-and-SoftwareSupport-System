package com.masai.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.EmployeeDao;
import com.masai.dao.EmployeeDaoImpl;
import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.Enginnerdao;
import com.masai.dao.Problemdao;
import com.masai.dao.ProblemdaoImpl;
import com.masai.dto.EmployeeDto;
import com.masai.dto.EmployeeDtoImpl;
import com.masai.dto.ProblemDto;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class EmployeeUi {
static void registerEmployee(Scanner sc) {
	System.out.println("Enter Username");
	String username=sc.next();
	System.out.println("Enter Password");
	String password=sc.next();
	EmployeeDto edt=new EmployeeDtoImpl(username, password);
	EmployeeDao ed=new EmployeeDaoImpl();
	try {
		String msg=ed.addEmployee(edt);
		System.out.println("\u001B[31m" + msg + "\u001B[0m");
		
	} catch (SomethingWentWrongException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
static void EmployeeLogin(Scanner sc) {
	System.out.println("Enter username");
	String username=sc.next();
System.out.println("Enter Password");
String password =sc.next();

EmployeeDao ed=new EmployeeDaoImpl();
try {
	String msg=ed.login(username, password);
	
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
} catch (InvalidCredentialsException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
static void registerComplaint(Scanner sc) {
	System.out.println("Enter Problem_desc");
	String Problem_desc=sc.next();
System.out.println("Enter employeeId");
int employee_id =sc.nextInt();
System.out.println("Enter ComplaintId");
int complaintId=sc.nextInt();
EmployeeDao ed=new EmployeeDaoImpl();
int msg;
try {
	msg = ed.registerComplaint(Problem_desc, employee_id,complaintId);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



	
	
}
static void getProblembyComplainId(Scanner sc) {
	System.out.println("Enter ComplaintId");
	int ComplaintId=sc.nextInt();	
	EmployeeDao pd=new EmployeeDaoImpl();
	try {
	ProblemDto pdt=	pd.getProblemByComplainId(ComplaintId);
	System.out.println(pdt);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
static void getStatusbyComplainId(Scanner sc) {
	System.out.println("Enter ComplaintId");
	int ComplaintId=sc.nextInt();	
	System.out.println("Enter employeeId");
	int employeeId=sc.nextInt();	
	EmployeeDao pd=new EmployeeDaoImpl();
	try {
	String pdt=	pd.getStatusByComplainId(ComplaintId,employeeId);
	System.out.println(pdt);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
static void getComplaintHistory(Scanner sc) {
	System.out.println("Enter employeeId");
	int employeeId=sc.nextInt();	
	EmployeeDao ed=new EmployeeDaoImpl();
	try {
String msg =	ed.getComplaintHistory(employeeId);
System.out.println("\u001B[31m" + msg + "\u001B[0m");
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
static void EmployeePasswordChange(Scanner sc) {
	EmployeeDao ed=new EmployeeDaoImpl();
	System.out.println("Enter Employee_Id");
	int Employee_Id=sc.nextInt();
	System.out.println("Enter old password");
	String old_password=sc.next();
	System.out.println("Enter new password");
	String new_password=sc.next();

	try {
		ed.changePassword(Employee_Id, old_password, new_password);
	} catch (InvalidCredentialsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}

}

