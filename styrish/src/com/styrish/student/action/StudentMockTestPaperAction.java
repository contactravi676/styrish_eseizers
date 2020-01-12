package com.styrish.student.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class StudentMockTestPaperAction extends ActionSupport implements ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private String mockTestId;
	private String courseId;
	private String userId;
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
	
	public String execute() {
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		if(session.getAttribute("courseIdSession")!=null) {
			courseId=(String) session.getAttribute("courseIdSession");
			userId=(String) session.getAttribute("userId");
		}

	System.out.println(courseId+":::::"+userId+"mockTestId>>>>"+mockTestId);	
		return SUCCESS;
		
		
	}

}
