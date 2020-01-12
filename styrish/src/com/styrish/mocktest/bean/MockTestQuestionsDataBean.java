package com.styrish.mocktest.bean;

import java.util.*;

import com.styrish.mocktest.dao.MockTestDaoImpl;

public class MockTestQuestionsDataBean {

	private List<Map<String,String>> mockQuestionsList;
	private Map<String,String> mockQuestionsMap;
	private String mockTestId;
	private String mockTestStatus;
	private String questionId;
	MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();

	public String getMockTestId() {
		return mockTestId;
	}
	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}
	public String getMockTestStatus() {
		return mockTestStatus;
	}
	public void setMockTestStatus(String mockTestStatus) {
		this.mockTestStatus = mockTestStatus;
	}



	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public Map<String, String> getMockQuestionsMap() {

		mockQuestionsMap=mockTestDaoImpl.getQuestion(questionId);

		return mockQuestionsMap;
	}
	public void setMockQuestionsMap(Map<String, String> mockQuestionsMap) {
		this.mockQuestionsMap = mockQuestionsMap;
	}
	public List<Map<String, String>> getMockQuestionsList() {
		mockQuestionsList=mockTestDaoImpl.getMockTestQuestions(mockTestId);

		return mockQuestionsList;
	}
	public void setMockQuestionsList(List<Map<String, String>> mockQuestionsList) {
		this.mockQuestionsList = mockQuestionsList;
	}






}
