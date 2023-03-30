package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.HodDao;
import com.masai.dao.*;
import com.masai.dto.HodDtoimpl;
import com.masai.exception.InvalidCredentialsException;

public class LoginUi {
static void Login(Scanner sc) {
	System.out.println("Enter username");
	String username=sc.next();
System.out.println("Enter Password");
String password =sc.next();

HodDao hd=new Hoddaoimpl();
try {
	String msg=hd.login(username, password);
	System.out.println("\u001B[31m" + msg + "\u001B[0m");
} catch (InvalidCredentialsException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



}
	
	
}
