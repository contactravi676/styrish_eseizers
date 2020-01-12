package com.styrish.student.databean;

import java.util.List;
import java.util.Map;

import com.styrish.mocktest.dao.MockTestDaoImpl;

public class StudentMockTestPaperDataBean {
	
	private String userId;
	private String mockTestId;
	private List<Map<String,String>> mockTestQuestionList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMockTestId() {
		return mockTestId;
	}
	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}
	public List<Map<String, String>> getMockTestQuestionList() {
		MockTestDaoImpl mockTestDaoImpl =new MockTestDaoImpl();	
		mockTestQuestionList=mockTestDaoImpl.getMockTestQuestions(mockTestId);
		
		
		return mockTestQuestionList;
	}
	public void setMockTestQuestionList(List<Map<String, String>> mockTestQuestionList) {
		this.mockTestQuestionList = mockTestQuestionList;
	}
	
	

}
