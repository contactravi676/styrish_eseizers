package com.styrish.courses.databean;

import java.util.List;
import java.util.Map;

import com.styrish.courses.dao.CoursesDaoImpl;

public class CourseTopicsDataBean {
	List<Map<String,String>> courseTopicList;
	private String courseId;
	private String subjectId;
	
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	CoursesDaoImpl coursesDaoImpl = new CoursesDaoImpl();
	public List<Map<String, String>> getCourseTopicList() {
		courseTopicList=coursesDaoImpl.getCourseTopics(courseId, subjectId);

		return courseTopicList;
	}

	public void setCourseTopicList(List<Map<String, String>> courseTopicList) {
		this.courseTopicList = courseTopicList;
	}


}
