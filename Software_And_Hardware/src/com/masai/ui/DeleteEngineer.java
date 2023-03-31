package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.Enginnerdao;
import com.masai.exception.NoRecordFoundException;

public class DeleteEngineer {
static void deleteEnginner(Scanner sc){
	System.out.println("Enter EnginnerId");
	int enginner_id=sc.nextInt();
	
Enginnerdao ed=new EnginnerDaoImpl();
try {
String msg =	ed.deleteEngineer(enginner_id);
System.out.println(msg);
	
} catch (NoRecordFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
