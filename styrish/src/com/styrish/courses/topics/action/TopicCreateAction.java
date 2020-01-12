package com.styrish.courses.topics.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicCreateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String courseId;
	private String subjectId;
	private String userId;
	private String topicName;
	private String topicDescription;
	
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
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
		 HttpSession session=ServletActionContext.getRequest().getSession(false);
		 if(session.getAttribute("courseIdSession")!=null) {
				courseId=(String) session.getAttribute("courseIdSession");
				userId=(String) session.getAttribute("userId");
					}
		
		if(topicName!=null) {
		CourseTopicsDaoImpl courseTopicsDaoImpl= new CourseTopicsDaoImpl();	
		courseTopicsDaoImpl.createTopics(this);
		}
		
		return SUCCESS;
	}

}
