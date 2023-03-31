package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.*;

import com.masai.dto.EngineerDto;
import com.masai.dto.EngineerDtoImpl;
import com.masai.exception.SomethingWentWrongException;

public class RegisterEngineer {
	static void RegisterEngineer(Scanner sc) {
		System.out.println("Enter username");
		String username=sc.next();
	System.out.println("Enter Password");
	String password =sc.next();
System.out.println("Enter category");
String category=sc.next();

Enginnerdao ed= new EnginnerDaoImpl();
	EngineerDto edt=new EngineerDtoImpl(username, password, category);
	try {
	String msg=ed.addEngineer(edt);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
	
	} catch (SomethingWentWrongException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



	}
}
