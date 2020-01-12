package com.styrish.courses.topics.action;

import java.util.*;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicDisplayDetailsAction extends ActionSupport {

	
	private static final long serialVersionUID = 1L;
	private List<Map<String,String>> videoList;
	private List<Map<String,String>> contentList;
	private List<Map<String,String>> exerciseList;
	private String topicId;
	private String topicName;
	
	public List<Map<String, String>> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Map<String, String>> videoList) {
		this.videoList = videoList;
	}
	public List<Map<String, String>> getContentList() {
		return contentList;
	}
	public void setContentList(List<Map<String, String>> contentList) {
		this.contentList = contentList;
	}
	
	public List<Map<String, String>> getExerciseList() {
		return exerciseList;
	}
	public void setExerciseList(List<Map<String, String>> exerciseList) {
		this.exerciseList = exerciseList;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String execute() {
		
		
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		Map topicMap=courseTopicsDaoImpl.getCourseTopicByTopicId(topicId);
		if(topicMap.get("topic_name")!=null) {
		topicName=(String) topicMap.get("topic_name");
		setTopicName(topicName);
		}
		
		return SUCCESS;
	}
	
}
