package com.styrish.mocktest.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.mocktest.dao.MockTestDaoImpl;

public class TutorMockTestDeleteQuestionsAction extends ActionSupport {
	
	
	private static final long serialVersionUID = 1L;
	private String mockTestId;
	private String questionId;
	
	
	 public String getMockTestId() {
		return mockTestId;
	}


	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}


	

	public String getQuestionId() {
		return questionId;
	}


	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String execute() throws Exception {
	    	
	    	MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();
	    	int status=mockTestDaoImpl.deleteQuestion(questionId);
	    	
	           return "success";
		}

}
