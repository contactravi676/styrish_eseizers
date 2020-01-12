package com.styrish.courses.databean;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.styrish.courses.dao.CoursesDaoImpl;

public class SubCoursesDataBean {
	
	private String courseId;
	private List<Map<String, String>> subCourseList;
	
	private CoursesDaoImpl coursesDaoImpl = new CoursesDaoImpl();
	
	
	public String getCourseId() {
		return courseId;
	}




	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}




	public List<Map<String, String>> getSubCourseList() {
	
		
		if((courseId==null)||(courseId.equals(""))) {
		 HttpSession session=ServletActionContext.getRequest().getSession(false);  
			
		if(session.getAttribute("courseIdSession")!=null) {
			courseId=(String) session.getAttribute("courseIdSession");
			}
		}
		
		subCourseList=coursesDaoImpl.getSubCourses(courseId);
		
		
		return subCourseList;
	}




	public void setSubCourseList(List<Map<String, String>> subCourseList) {
		this.subCourseList = subCourseList;
	}




	

}
