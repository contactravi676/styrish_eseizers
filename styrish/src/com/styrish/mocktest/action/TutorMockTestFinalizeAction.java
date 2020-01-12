package com.styrish.mocktest.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.mocktest.dao.MockTestDaoImpl;

public class TutorMockTestFinalizeAction extends ActionSupport {


	private static final long serialVersionUID = 1L;
	private String mockTestId;




	public String getMockTestId() {
		return mockTestId;
	}




	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}




	public String execute() throws Exception {

		MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();
		mockTestDaoImpl.updateMockTest(mockTestId);


		return "success";
	}
}
