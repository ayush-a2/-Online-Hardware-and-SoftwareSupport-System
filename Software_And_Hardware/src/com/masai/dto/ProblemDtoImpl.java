package com.masai.dto;

public class ProblemDtoImpl implements ProblemDto{
	  private int problemId;
	 private int complainId;
	    private String problemDesc;
	    private String status;
	    private int engineerId;

	


		public ProblemDtoImpl(int problemId, int complainId, String problemDesc, String status, int engineerId) {
		super();
		this.problemId = problemId;
		this.complainId = complainId;
		this.problemDesc = problemDesc;
		this.status = status;
		this.engineerId = engineerId;
	
	}
		
	

		public int getProblemId() {
			return problemId;
		}
		public void setProblemId(int problemId) {
			this.problemId = problemId;
		}
		public int getComplainId() {
			return complainId;
		}
		public void setComplainId(int complainId) {
			this.complainId = complainId;
		}
		public String getProblemDesc() {
			return problemDesc;
		}
		public void setProblemDesc(String problemDesc) {
			this.problemDesc = problemDesc;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getEngineerId() {
			return engineerId;
		}
		public void setEngineerId(int engineerId) {
			this.engineerId = engineerId;
		}
		@Override
		public String toString() {
			return "problemId=" + problemId + ", complainId=" + complainId + ", problemDesc="
					+ problemDesc + ", status=" + status +   "";
		}
		
}
