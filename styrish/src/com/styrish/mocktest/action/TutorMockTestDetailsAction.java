package com.styrish.mocktest.action;

import com.opensymphony.xwork2.ActionSupport;

public class TutorMockTestDetailsAction  extends ActionSupport {
	
 static final long serialVersionUID = 1L;
	private String mockTestId;

	public String getMockTestId() {
		return mockTestId;
	}

	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}
	
	
	public String execute() throws Exception {
		
		
		
		return "success";
	}

}
