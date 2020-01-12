package com.styrish.courses.databean;

import java.util.List;
import java.util.Map;

import com.styrish.courses.dao.CourseTopicDaoImpl;



public class CourseTopicComponentDataBean {
	
	private String topicId;
	private  Map topicVideoMap;
	private Map topicContentMap;
	private List topicExerciseList;
	CourseTopicDaoImpl courseTopicDaoImpl =new CourseTopicDaoImpl();	
	public String getTopicId() {
		
		
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public Map getTopicVideoMap() {
		topicVideoMap=courseTopicDaoImpl.getCourseTopicsVideos(topicId);
	
		return topicVideoMap;
	}
	public void setTopicVideoMap(Map topicVideoMap) {
		this.topicVideoMap = topicVideoMap;
	}
	public Map getTopicContentMap() {
	
		topicContentMap=courseTopicDaoImpl.getCourseTopicsContent(topicId);
	
		return topicContentMap;
		
	}
	public void setTopicContentMap(Map topicContentMap) {
		this.topicContentMap = topicContentMap;
	}
	public List getTopicExerciseList() {
		topicExerciseList=courseTopicDaoImpl.getCourseTopicsExercise(topicId);
	
		return topicExerciseList;
	}
	public void setTopicExerciseList(List topicExerciseList) {
		this.topicExerciseList = topicExerciseList;
	}
	
	
	
	
	
	

}
