package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.ProblemDto;
import com.masai.dto.ProblemDtoImpl;
import com.masai.exception.NoRecordFoundException;

public class ProblemdaoImpl implements Problemdao {


	@Override
	public List<ProblemDto> getAllProblems() throws NoRecordFoundException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		  Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<ProblemDto> problemList = new ArrayList<>();

	        try {
	            conn = Dbutilis.connectToDb();
	            String query = "SELECT * FROM problem";
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	            	int problemId = rs.getInt("problem_id");
	                int complainId = rs.getInt("complain_id");
	                String problemDesc = rs.getString("problem_desc");
	                String status = rs.getString("status");
	                int engineerId = rs.getInt("engineer_id");
	                ProblemDto problemDto = new ProblemDtoImpl(problemId,complainId, problemDesc, status, engineerId);
	                problemList.add(problemDto);
	            }
	        } finally {
	            Dbutilis.closeConnection(conn);;
	        }

	        if (problemList.isEmpty()) {
	            throw new NoRecordFoundException("No record found");
	        }

	        return problemList;
	    }


	   @Override
	    public void updateProblem(ProblemDto problem) throws SQLException {
		  
		   Connection conn=null;
		   try {
			   conn=Dbutilis.connectToDb();
	        String query = "UPDATE problem SET complain_Id=?, problem_Desc=?, status=?, engineer_Id=? WHERE problem_Id=?";
	        PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.setInt(1, problem.getComplainId());
	        stmt.setString(2, problem.getProblemDesc());
	        stmt.setString(3, problem.getStatus());
	        stmt.setInt(4, problem.getEngineerId());
	        stmt.setInt(5, problem.getProblemId());
	        stmt.executeUpdate();
	        stmt.close();
	    }catch(SQLException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
	   }

    @Override
    public String assignProblem(int problem_Id, int engineer_Id) throws NoRecordFoundException {
        String msg = null;
        try {
            ProblemDto problem = getProblemById(problem_Id);
            problem.setEngineerId(engineer_Id);
            problem.setStatus("Assigned");
            updateProblem(problem);
            msg = "Problem " + problem_Id + " assigned to engineer " + engineer_Id;
        } catch (SQLException | ClassNotFoundException e) {
            throw new NoRecordFoundException("No record found");
        }
        return msg;
    }

    public ProblemDto getProblemById(int problemId) throws SQLException, ClassNotFoundException, NoRecordFoundException {
    	  Connection conn = null;
    	    conn = Dbutilis.connectToDb();
    	    String query = "SELECT * FROM problem WHERE problem_Id=?";
    	    PreparedStatement stmt = conn.prepareStatement(query);
    	    stmt.setInt(1, problemId);
    	    ResultSet rs = stmt.executeQuery();
    	    ProblemDto problem = null;
    	    if (rs.next()) {
    	        problem = new ProblemDtoImpl(rs.getInt("problem_Id"), rs.getInt("complain_Id"), rs.getString("problem_Desc"), rs.getString("status"), rs.getInt("engineer_Id"));
    	    }
    	    rs.close();
    	    stmt.close();
    	    if (problem == null) {
    	        throw new NoRecordFoundException("No record found");
    	    }
    	    return problem;
    }
		
	}	


