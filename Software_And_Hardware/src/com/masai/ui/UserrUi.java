package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.EmployeeDao;
import com.masai.dao.EmployeeDaoImpl;
import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.Enginnerdao;
import com.masai.dao.HodDao;
import com.masai.dao.Hoddaoimpl;
import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;

public class UserrUi {
static boolean login(Scanner sc) {
	System.out.println("Enter username");
	String username=sc.next();
System.out.println("Enter Password");
String password =sc.next();

HodDao hd=new Hoddaoimpl();
try {
	String msg=hd.login(username, password);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
	
} catch (InvalidCredentialsException | NoRecordFoundException e) {
	// TODO Auto-generated catch block
	System.out.println(e);
	return false;
}
	
	
return true;
}
static boolean EngineerLogin(Scanner sc) {
	System.out.println("Enter username");
	String username=sc.next();
System.out.println("Enter Password");
String password =sc.next();

Enginnerdao ed=new EnginnerDaoImpl();
try {
	String msg=ed.login(username, password);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
} catch (InvalidCredentialsException | NoRecordFoundException e) {
	// TODO Auto-generated catch block
System.out.println(e);
	return false;
}

return true;
}
static boolean EmployeeLogin(Scanner sc) {
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
	System.out.println(e);
	return false;
}
return true;

}
}