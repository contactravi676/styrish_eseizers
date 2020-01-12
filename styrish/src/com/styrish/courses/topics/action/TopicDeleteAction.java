package com.styrish.courses.topics.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicDeleteAction extends ActionSupport {
	

	private static final long serialVersionUID = 1L;
    private String topicId;
	
	public String getTopicId() {
		return topicId;
	}

   public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
	public String execute() {
		if(topicId!=null) {
			CourseTopicsDaoImpl courseTopicsDaoImpl= new CourseTopicsDaoImpl();	
			courseTopicsDaoImpl.deleteTopics(topicId);
			}
		
		
		return SUCCESS;
	
	}

}
