package com.masai.ui;

import java.sql.SQLException;
import java.util.List;

import com.masai.dao.Problemdao;
import com.masai.dao.ProblemdaoImpl;
import com.masai.dto.EngineerDto;
import com.masai.dto.ProblemDto;
import com.masai.exception.NoRecordFoundException;

public class SeeAllProblemUi {
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
}

