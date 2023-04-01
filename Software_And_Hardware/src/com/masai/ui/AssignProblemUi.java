package com.masai.ui;


import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import com.masai.dto.ProblemDto;

import com.masai.exception.InvalidCredentialsException;
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
static void viewProblems(Scanner sc) throws NoRecordFoundException  {
	Enginnerdao ed=new EnginnerDaoImpl();
	System.out.println("Enter Enginner ID");
	int engineer_id=sc.nextInt();
    try {
        List<ProblemDto> problems = ed.getProblemsByEngineerId(engineer_id);
        if (problems.isEmpty()) {
            throw new NoRecordFoundException("No problems found for engineer with id " + engineer_id);
        }
        System.out.println("Problems assigned to engineer " + engineer_id + ":");
        for (ProblemDto problem : problems) {
            System.out.println(problem.toString());
        }
    } catch (SQLException | ClassNotFoundException | NoRecordFoundException e) {
        throw new NoRecordFoundException("No record found");
    }
}
static void updateStatus(Scanner sc) throws NoRecordFoundException  {
	Enginnerdao ed=new EnginnerDaoImpl();
	System.out.println("Enter problem_id");
	int problem_id=sc.nextInt();
	System.out.println("Enter Status");
	String status=sc.next();
    try {
    ed.updateProblemStatus(problem_id, status);
        
    } catch (SQLException | ClassNotFoundException e ) {
        throw new NoRecordFoundException("No record found");
    }
}
static void viewProblemsAttendedByEngineer(Scanner sc) throws NoRecordFoundException {
	Enginnerdao ed=new EnginnerDaoImpl();
	System.out.println("Enter Enginner ID");
	int engineer_id=sc.nextInt();
    try {
        List<ProblemDto> problems =ed.getProblemsAttendedByEngineer(engineer_id);
        System.out.println("Problems attended by engineer " + engineer_id + ":");
        for (ProblemDto problem : problems) {
            System.out.println(problem.toString());
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println(e.getMessage());
    }
}
static void changePassword(Scanner sc) {
Enginnerdao ed=new EnginnerDaoImpl();
System.out.println("Enter Engineer_Id");
int engineer_id=sc.nextInt();
System.out.println("Enter old password");
String old_password=sc.next();
System.out.println("Enter new password");
String new_password=sc.next();

try {
	ed.changePassword(engineer_id, old_password, new_password);
} catch (InvalidCredentialsException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
}
