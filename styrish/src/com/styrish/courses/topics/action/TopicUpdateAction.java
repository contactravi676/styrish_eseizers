package com.styrish.courses.topics.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicUpdateAction extends ActionSupport {

	
	private static final long serialVersionUID = 1L;
	
	private String topicId;
	private String topicName;
	private String topicDescription;
	
	
	
	
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




	public String getTopicDescription() {
		return topicDescription;
	}




	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}




	public String execute() {
		if(topicId!=null) {
			CourseTopicsDaoImpl courseTopicsDaoImpl= new CourseTopicsDaoImpl();	
			courseTopicsDaoImpl.updateTopics(this);
			}
		
		
		return SUCCESS;
	
	}

}
