package com.styrish.mocktest.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.mocktest.dao.MockTestDaoImpl;

public class TutorMockTestAddQuestionsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mockTestId;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctOption;
	private String questionHint;
	public String getMockTestId() {
		return mockTestId;
	}
	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	public String getQuestionHint() {
		return questionHint;
	}
	public void setQuestionHint(String questionHint) {
		this.questionHint = questionHint;
	}


	public String execute() throws Exception {

		MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();
		int status=mockTestDaoImpl.createMockTestQuestion(this);

		return "success";
	}


}
