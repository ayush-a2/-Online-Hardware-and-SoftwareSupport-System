package com.masai.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.masai.dao.ProblemdaoImpl;
import com.masai.dto.ProblemDto;
import com.masai.exception.NoRecordFoundException;
import com.masai.dao.*;
public class AssignProblemUi {
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
