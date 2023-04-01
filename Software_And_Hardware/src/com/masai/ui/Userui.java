package com.masai.ui;

import java.util.Scanner;

import com.masai.exception.InvalidCredentialsException;
import com.masai.exception.NoRecordFoundException;

public class Userui {

	public static void main(String[] args) throws NoRecordFoundException{
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		do {
			System.out.println("1.Login Hod");
			System.out.println("2.Register Engineer");
			System.out.println("3.List of Enginner");
			System.out.println("4.Delete of Enginner");
			System.out.println("5.See All Raised Problem");
			System.out.println("6.Assign Problem to Engineer");
			System.out.println("7.Enginnerr Login");
//			System.out.println("8.Assign Problem");
			System.out.println("9.View problem");
			System.out.println("10.update Status");
			System.out.println("11.viewProblemsAttendedByEngineer");
			System.out.println("12.changePasswordEngineer");
			System.out.println("13.registerEmployee");
			System.out.println("14.EmployeeLogin");
			System.out.println("15.registerComplaint");
			System.out.println("16.getProblembyComplainId");
			System.out.println("17.getStatusbyComplainId");
			System.out.println("18.getComplaintHistory");
			System.out.println("19.EmployeePasswordChange");
			System.out.println("0.Exit");
			System.out.println("Enter Selection");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:LoginUi.Login(sc);
			break;
			case 2:RegisterEngineer.RegisterEngineer(sc);
			break;
			case 3:GetEnginnerUi.getEnginner();
			break;
			case 4:DeleteEngineer.deleteEnginner(sc);
			break;
			case 5:SeeAllProblemUi.seeProblem();
			break;
			case 6:AssignProblemUi.assignProblem(sc);;
			break;
			case 7:EnginnerLoginUi.EngineerLogin(sc);
			break;
			
//			case 8:AssignProblemUi.assignProblem(sc);
//			break;
			case 9:AssignProblemUi.viewProblems(sc);
			break;
			case 10:AssignProblemUi.updateStatus(sc);;
			break;
			case 11:AssignProblemUi.viewProblemsAttendedByEngineer(sc);
			break;
			case 12:AssignProblemUi.changePassword(sc);
			break;
			case 13:EmployeeUi.registerEmployee(sc);
			break;
			case 14:EmployeeUi.EmployeeLogin(sc);
			break;
			case 15:EmployeeUi.registerComplaint(sc);
			break;
			case 16:EmployeeUi.getProblembyComplainId(sc);
			break;
			case 17:EmployeeUi.getStatusbyComplainId(sc);
			break;
			case 18:EmployeeUi.getComplaintHistory(sc);
			break;
			case 19:EmployeeUi.EmployeePasswordChange(sc);
			break;
			case 0 :System.out.println("Exit");
			}
		}while(choice!=0);
		sc.close();
			
	}
	
	
}
