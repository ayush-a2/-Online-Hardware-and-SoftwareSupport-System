package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.Enginnerdao;
import com.masai.dao.HodDao;
import com.masai.dao.Hoddaoimpl;
import com.masai.exception.InvalidCredentialsException;

public class EnginnerLoginUi {
static void EngineerLogin(Scanner sc) {
	System.out.println("Enter username");
	String username=sc.next();
System.out.println("Enter Password");
String password =sc.next();

Enginnerdao ed=new EnginnerDaoImpl();
try {
	String msg=ed.login(username, password);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
} catch (InvalidCredentialsException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
}
