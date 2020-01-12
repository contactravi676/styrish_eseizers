package com.styrish.student.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class StudentMockTestPaperSubmitAction extends ActionSupport implements ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private String mockTestId;
	private String courseId;
	private String userId;
	private String questionId;
	private String correctOption;
	
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private ServletContext servletContext;


	public void setServletContext(ServletContext context) {
		servletContext=context;
	}
	
	
	
	
	public String getMockTestId() {
		return mockTestId;
	}




	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}




	public String getCourseId() {
		return courseId;
	}




	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	


	public String getQuestionId() {
		return questionId;
	}




	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}




	public String getCorrectOption() {
		return correctOption;
	}




	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
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




	public String execute() {
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		if(session.getAttribute("courseIdSession")!=null) {
			courseId=(String) session.getAttribute("courseIdSession");
			userId=(String) session.getAttribute("userId");
		}

	
		return SUCCESS;
		
		
	}

}
