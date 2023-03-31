package com.masai.ui;

import com.masai.dao.EnginnerDaoImpl;
import com.masai.dto.EngineerDto;
import com.masai.exception.NoRecordFoundException;

import java.util.List;

import com.masai.dao.*;
public class GetEnginnerUi {
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
}
