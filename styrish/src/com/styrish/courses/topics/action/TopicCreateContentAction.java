package com.styrish.courses.topics.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicCreateContentAction extends ActionSupport{

	
	private static final long serialVersionUID = 1L;
	
	private String courseTopicChoice;
	private String courseId;
	private String userId;
	private String contentTitle;
	private String contents;
	private String topicId;
	
	public String getCourseTopicChoice() {
		return courseTopicChoice;
	}
	public void setCourseTopicChoice(String courseTopicChoice) {
		this.courseTopicChoice = courseTopicChoice;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
	
	public String execute() {
		 HttpSession session=ServletActionContext.getRequest().getSession(false);
		 if(session.getAttribute("courseIdSession")!=null) {
				courseId=(String) session.getAttribute("courseIdSession");
				userId=(String) session.getAttribute("userId");
					}
   CourseTopicsDaoImpl courseTopicsDaoImpl=new CourseTopicsDaoImpl();
   courseTopicsDaoImpl.insertContent(this);
		
		return SUCCESS;
		
		
	}
	

}
