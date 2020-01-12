package com.styrish.mocktest.action;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
import com.styrish.mocktest.dao.MockTestDaoImpl;

public class TutorMockTestCreateAction extends ActionSupport {
	
private static final long serialVersionUID = 1L;

private String courseId;
private String userId;
private String subCourseId;
private String mocktestName;
private String totalQuestions;
private String totalTime;
private String examGuidlines;
private String mocktestStatus;
private String mockTestId;





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




public String getSubCourseId() {
	return subCourseId;
}




public void setSubCourseId(String subCourseId) {
	this.subCourseId = subCourseId;
}




public String getMocktestName() {
	return mocktestName;
}




public void setMocktestName(String mocktestName) {
	this.mocktestName = mocktestName;
}





public String getTotalQuestions() {
	return totalQuestions;
}




public void setTotalQuestions(String totalQuestions) {
	this.totalQuestions = totalQuestions;
}




public String getTotalTime() {
	return totalTime;
}




public void setTotalTime(String totalTime) {
	this.totalTime = totalTime;
}




public String getExamGuidlines() {
	return examGuidlines;
}




public void setExamGuidlines(String examGuidlines) {
	this.examGuidlines = examGuidlines;
}




	public String getMocktestStatus() {
	return mocktestStatus;
}




public void setMocktestStatus(String mocktestStatus) {
	this.mocktestStatus = mocktestStatus;
}








	public String getMockTestId() {
	return mockTestId;
}




public void setMockTestId(String mockTestId) {
	this.mockTestId = mockTestId;
}




	public String execute() throws Exception {
	
		MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();
		 HttpSession session=ServletActionContext.getRequest().getSession(false);  
		
			if(session.getAttribute("courseIdSession")!=null) {
		courseId=(String) session.getAttribute("courseIdSession");
			}
			if(session.getAttribute("userId")!=null) {
				userId=(String) session.getAttribute("userId");	
			}
		mockTestDaoImpl.createMockTest(this);
		
	Map<String,String>  mockTestMap	=mockTestDaoImpl.getMockTestByName(mocktestName, subCourseId);
	mockTestId=mockTestMap.get("mocktest_id");
		
		return "success";
	}

}
