package com.styrish.student.databean;
import java.util.*;

import com.styrish.student.dao.StudentMockTestDaoImpl;

public class StudentMockTestDataBean {
	
	private String userId;
	private String courseId;
	private List<Map<String,String>> mockTestList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public List<Map<String, String>> getMockTestList() {
		StudentMockTestDaoImpl studentMockTestDaoImpl =new StudentMockTestDaoImpl();
		mockTestList=studentMockTestDaoImpl.getMockTestByStudent(courseId);
		
		return mockTestList;
	}
	public void setMockTestList(List<Map<String, String>> mockTestList) {
		this.mockTestList = mockTestList;
	}
	
	

}
