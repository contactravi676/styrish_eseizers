package com.styrish.courses.topics.action;

import java.util.*;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicEditAction extends ActionSupport {


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
			Map<String, String> topicMap=	courseTopicsDaoImpl.getCourseTopicByTopicId(topicId);
			topicName=(String) topicMap.get("topic_name");
			topicDescription=(String) topicMap.get("shortdescription");
			setTopicName(topicName);
			setTopicDescription(topicDescription);
		}


		return SUCCESS;

	}

}
