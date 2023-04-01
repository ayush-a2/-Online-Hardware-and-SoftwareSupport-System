package com.masai.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.EnginnerDaoImpl;
import com.masai.dao.Enginnerdao;
import com.masai.dao.Problemdao;
import com.masai.dao.ProblemdaoImpl;
import com.masai.dto.EngineerDto;
import com.masai.dto.EngineerDtoImpl;
import com.masai.dto.ProblemDto;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class HoDUi {
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
	static void getEnginner() {
		Enginnerdao ed=new EnginnerDaoImpl();
		
		
	try {
		List<EngineerDto> list =ed.getAllEngineers();
		list.forEach(System.out::println);
	} catch (NoRecordFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
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
	static void seeProblem() {
		Problemdao pd=new ProblemdaoImpl();
		try {
			List<ProblemDto> list =pd.getAllProblems();
			list.forEach(System.out::println);
		} catch (NoRecordFoundException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
	static void assignProblem(Scanner sc) {
		
		System.out.println("Enter Problem ID");
		int problem_id=sc.nextInt();
		System.out.println("Enter EngineervID");
		int Enginner_id=sc.nextInt();
		Problemdao pd=new ProblemdaoImpl();
	try {
	String pdt=	pd.assignProblem(problem_id, Enginner_id);
	System.out.println(pdt);
	} catch (NoRecordFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
