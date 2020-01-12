package com.styrish.mocktest.bean;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.styrish.mocktest.dao.MockTestDaoImpl;

public class MockTestDataBean {
	
	private List<Map<String,String>> mockTestList=null;
	private String courseId=null;
	//private Map<String,String> mockTestByStatus=null;
	private String mockTestStatus=null;
	
	private String mockTestId=null;
	private Map<String,String> mockTestMap=new HashMap<String,String>();
	
	
	MockTestDaoImpl mockTestDaoImpl=new MockTestDaoImpl();
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	
public String getMockTestStatus() {
		return mockTestStatus;
	}

	public void setMockTestStatus(String mockTestStatus) {
		this.mockTestStatus = mockTestStatus;
	}

	public List<Map<String, String>> getMockTestList() {
		 HttpSession session=ServletActionContext.getRequest().getSession(false);
		 if(session.getAttribute("courseIdSession")!=null) {
				courseId=(String) session.getAttribute("courseIdSession");
					}
		mockTestList=mockTestDaoImpl.getMockTest(courseId, mockTestStatus);
		
		return mockTestList;
	}

	public void setMockTestList(List<Map<String, String>> mockTestList) {
		this.mockTestList = mockTestList;
	}

	public String getMockTestId() {
		return mockTestId;
	}

	public void setMockTestId(String mockTestId) {
		this.mockTestId = mockTestId;
	}

	public Map<String, String> getMockTestMap() {
		mockTestMap=mockTestDaoImpl.getMockTestByMockTestId(mockTestId);
	
		return mockTestMap;
	}

	public void setMockTestMap(Map<String, String> mockTestMap) {
		this.mockTestMap = mockTestMap;
	}
	
	
	
	

	

	
	
	
	
	
	
	

}
