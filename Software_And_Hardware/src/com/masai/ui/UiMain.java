package com.masai.ui;

import java.util.Scanner;

import com.masai.exception.NoRecordFoundException;



public class UiMain {
static void displayHodMenu() {
	System.out.println("1.Register a New Engineer ");
	System.out.println("2.See List of All Engineers");
	System.out.println("3.Delete Engineer From System");
	System.out.println("4. See All Raised Problem");
	System.out.println("5.Assign Problem to Engineer");
	System.out.println("0. Logout");
}
static void displayEngineerMenu() {
	System.out.println("1.Engineeer View The Assigned Problem");
	System.out.println("2.Update Status Of Problem");
	System.out.println("3.List Of problem Attended By ");
	System.out.println("4.Enginner Can Change Password");
	System.out.println("0. Logout");
}
static void displayEmployeeMenu() {
	System.out.println("1.Register employee");
	System.out.println("2.Register any complain and genrate Complain id");
	System.out.println("3.Employee See The Status of their problem ");
	System.out.println("4.see all complain history raised by him/her.");
	System.out.println("5.Employee Can change Password");
	System.out.println("0. Logout");	
	
}

static void EmployeeLogin(Scanner sc){
	if(!UserrUi.EmployeeLogin(sc))
		return;
	int choice = 0;
	do {
		displayEmployeeMenu();
		System.out.print("Enter selection ");
		choice = sc.nextInt();
		switch(choice) {
		case 0:System.out.println("Thank you, Visit again");
		break;
		case 1:EmployeeUi.registerEmployee(sc);
		break;
		case 2:EmployeeUi.getProblembyComplainId(sc);
		break;
		case 3:EmployeeUi.getStatusbyComplainId(sc);
		break;
		case 4:EmployeeUi.getComplaintHistory(sc);
		break;
		case 5:EmployeeUi.EmployeePasswordChange(sc);
		break;
			default:
				System.out.println("Invalid Selection, try again");
		}
	}while(choice != 0);
}
static void EngineerLogin(Scanner sc) throws NoRecordFoundException {
	if(!UserrUi.EngineerLogin(sc)) {
		return;		
	}
		
	int choice = 0;
	do {
		displayEngineerMenu();
		System.out.print("Enter selection ");
		choice = sc.nextInt();
		switch(choice) {
		case 0:System.out.println("Thank you, Visit again");
		break;
		case 1:EngineerUi.viewProblems(sc);
		break;
		case 2:EngineerUi.updateStatus(sc);
		break;
		case 3:EngineerUi.viewProblemsAttendedByEngineer(sc);
		break;
		case 4:EngineerUi.changePassword(sc);
		break;
	
			default:
				System.out.println("Invalid Selection, try again");
		}
	}while(choice != 0);
}
static void HODLogin(Scanner sc) {

	if(!UserrUi.login(sc)) {
		return;
	}
	//you are here means login is successful
	int choice = 0;
	do {
		displayHodMenu();
		System.out.print("Enter selection ");
		choice = sc.nextInt();
		switch(choice) {
		case 0:System.out.println("Thank you, Visit again");
		break;
		case 1:HoDUi.RegisterEngineer(sc);
		break;
		case 2:HoDUi.getEnginner();
		break;
		case 3:HoDUi.deleteEnginner(sc);
		break;
		case 5:HoDUi.assignProblem(sc);
		break;
		case 4:HoDUi.seeProblem();
		break;	
			default:
				System.out.println("Invalid Selection, try again");
		}
	}while(choice != 0);
	
	
}
	
	public static void main(String[] args) throws NoRecordFoundException {
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	do {
	System.out.println("1. HOD Login\n2. Engineer Login\n3. Employee Login\n0. Exit");	
	choice = sc.nextInt();
	switch(choice) {
	case 0:System.out.println("Thank you, Visit again");
		break;
	case 1:
		HODLogin(sc);
		break;
	case 2:
		EngineerLogin(sc);
		break;
	case 3:
		EmployeeLogin(sc);
		break;
	
	
	default:
		System.out.println("Invalid Selection, try again");	
	}
	}while(choice != 0);
	sc.close();
}
}