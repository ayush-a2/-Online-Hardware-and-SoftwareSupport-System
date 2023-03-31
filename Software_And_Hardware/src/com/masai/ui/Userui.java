package com.masai.ui;

import java.util.Scanner;

public class Userui {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int choice=0;
		
		do {
			System.out.println("1.Login Hod");
			System.out.println("2.Register Engineer");
			System.out.println("3.List of Enginner");
			System.out.println("4.Delete of Enginner");
			System.out.println("5.See All Raised Problem");
			System.out.println("6.Assign Problem to Engineer");
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
			case 0 :System.out.println("Exit");
			}
		}while(choice!=0);
		sc.close();
			
	}
	
	
}
